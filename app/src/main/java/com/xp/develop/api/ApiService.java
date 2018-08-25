package com.xp.develop.api;


import com.xp.develop.base.BaseResponse;
import com.xp.develop.entity.Login;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author : xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/25
 * desc  :  utils about initialization
 */
public interface ApiService {

    @POST("query")
//    @POST("province-count")
    Observable<BaseResponse<List<Login>>> login(@QueryMap Map<String, String> map);

    @POST("query")
    Observable<BaseResponse<List<Login>>> logout(@QueryMap Map<String, String> map);

//    // 登录的请求
//    @POST("loginManage/login")
//    Observable<BaseResponse<Login>> login(@QueryMap Map<String, String> map);

//    //上传图片
//    @POST("file/up")
//    @Multipart
//    Observable<BaseResponse<List<UploadFile>>> upload(@Part List<MultipartBody.Part> parts);


}
