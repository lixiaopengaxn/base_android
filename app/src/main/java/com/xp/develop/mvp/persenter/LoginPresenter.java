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
        map.put("location", companyName);
        map.put("key", "MJX11XSAPG");
        map.put("language", "zh-Hans");
        map.put("unit", "c");
        model.okHttp(context, Api.getApiService().login(map), getView().bindLifecycle(), new ObserverResponseListener<LoginModel>() {
            @Override
            public void onNext(LoginModel model) {

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
