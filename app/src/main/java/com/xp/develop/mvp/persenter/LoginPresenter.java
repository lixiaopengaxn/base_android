package com.xp.develop.mvp.persenter;

import android.content.Context;

import com.xp.develop.api.Api;
import com.xp.develop.mvp.contract.LoginContract;
import com.xp.develop.mvp.entity.LoginModel;
import com.xp.develop.mvp.model.OkHttpModel;
import com.xp.develop.progress.ObserverResponseListener;
import com.xp.develop.utils.ExceptionHandle;

import java.util.HashMap;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/14
 * desc  :  utils about initialization
 */
public class LoginPresenter extends LoginContract.Presenter {

    private OkHttpModel model;
    private Context context;

    public LoginPresenter(Context context) {
        this.model = new OkHttpModel();
        this.context = context;
    }

    @Override
    public void requsetLogin(String companyName, String uName, String uPassword) {
        HashMap map = new HashMap();
        map.put("orgCode", "glf");
        map.put("userName", uName);
        map.put("password", uPassword);
        map.put("currentLang", "2");
        map.put("endpointInfo", "{\"ID\":\"00-33-67-DE-B4\",\"systemType\":\"ios\",\"systemVersion\":\"4.3.3\"}");
        model.okHttp(context, Api.getApiService().loginpost(map), getView().bindLifecycle(), new ObserverResponseListener<String>() {
            @Override
            public void onNext(String model) {

                if (getView() != null) {
                    getView().loginData(model);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    getView().onErrorMsg(e.message);
                }
            }

            @Override
            public void onSuccessCodeError(String code) {
                getView().onErrorMsg(code);
            }

            @Override
            public void onComplete() {
//                getView().onSuccessMsg("完成");
            }
        });


    }
}
