package com.xp.develop.mvp.persenter;

import android.content.Context;

import com.xp.develop.api.Api;
import com.xp.develop.base.BaseResponse;
import com.xp.develop.mvp.contract.LoginContract;
import com.xp.develop.mvp.entity.LoginModel;
import com.xp.develop.mvp.model.OkHttpModel;
import com.xp.develop.progress.ObserverResponseListener;
import com.xp.develop.utils.ExceptionHandle;
import com.xp.develop.utils.ToastUtil;

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
        map.put("orgCode", companyName);
        map.put("loginName", uName);
        map.put("loginPass", uPassword);
        model.okHttp(context, Api.getApiService().login(map), getView().bindLifecycle(), new ObserverResponseListener<BaseResponse<LoginModel>>() {
            @Override
            public void onNext(BaseResponse<LoginModel> model) {

                if (getView() != null) {
//                    getView().loginData(model.getData());
//                    ToastUtil.showLongToast(model.getData().toString()+"");
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    getView().onErrorMsg(e.message);
                }
            }

            @Override
            public void onCodeError(String code) {
                getView().onErrorMsg(code);
            }

            @Override
            public void onComplete() {
                getView().onSuccessMsg("");
            }
        });


    }
}
