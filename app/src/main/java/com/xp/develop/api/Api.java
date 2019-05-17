package com.xp.develop.api;

import com.xp.develop.base.BaseApi;

/**
 * author : xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/25
 * desc  :  utils about initialization
 * https://blog.csdn.net/a634930172a/article/details/80769790
 */

public class Api {



    private String baseUrl = "http://192.168.2.156:8090/";
//    private String baseUrl = "http://hbhz.sesdf.org/api/";
//    http://www.kuaidi100.com/query?type=快递公司代号&postid=快递单号

    private volatile static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (apiService == null) {
                    new Api();
                }
            }
        }
        return apiService;
    }

    private Api() {
        BaseApi baseApi = new BaseApi();
        apiService = baseApi.getRetrofit(baseUrl).create(ApiService.class);
    }
}
