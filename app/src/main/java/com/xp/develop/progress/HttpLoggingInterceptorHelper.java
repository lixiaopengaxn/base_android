package com.xp.develop.progress;

import com.xp.develop.utils.AppUtils;
import com.xp.develop.utils.UtilsLog;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author : xp
 * @blog :  https://blog.csdn.net/qq_38729449
 * @create :  2019/3/7 14:55
 * @Describe :  utils about initialization
 */
public class HttpLoggingInterceptorHelper implements Interceptor{


    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原先的请求
        Request request = chain.request();



        //重新构建url
        HttpUrl.Builder builder = request.url().newBuilder();
        //如果是post请求的话就把参数重新拼接一下，get请求的话就可以直接加入公共参数了
        if(request.method().equals("POST")){
            FormBody body = (FormBody) request.body();
            for(int i = 0; i < body.size();i++){
//                UtilsLog.i("RequestFatory",body.name(i) + "---" + body.value(i));
                builder.addQueryParameter(body.name(i),body.value(i));
            }
        }
        //这里是我的2个公共参数
//        builder.addQueryParameter("key","MJX11XSAPG")
//                .addQueryParameter("language","zh-Hans");
        //新的url
        HttpUrl httpUrl = builder.build();
        Request requests = request.newBuilder()
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .method(request.method(), request.body())
                .url(httpUrl).build();


        Response response = chain.proceed(requests);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();


        if (!bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }

            if (!isPlaintext(buffer)) {
                return response;
            }

            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
                UtilsLog.e("------------------------------------------- S T A R ------------------------------------------------------\n"+" ");
                UtilsLog.e(" 请求的--模式-->>> 模式 : "+ response.request().method());
                UtilsLog.e(" 请求的--url-->>>  Url : "+response.request().url().toString() + "\n" + " ");

                AppUtils.getInstance().URLRequest(response.request().url().toString());
                UtilsLog.e( "     我是来凑热闹的～·～ " + "\n"+" ");
                UtilsLog.e(" 请求的--body-->>> Body : " + result + "\n" + " ");
                UtilsLog.e("-------------------------------------------- E N D -------------------------------------------------------");
                //得到所需的string，开始判断是否异常
                //***********************do something*****************************

            }

        }

        return response;
    }
}
