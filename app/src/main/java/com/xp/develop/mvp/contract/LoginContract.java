package com.xp.develop.mvp.contract;

import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseResponse;
import com.xp.develop.base.BaseView;
import com.xp.develop.mvp.entity.LoginModel;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/14
 * desc  :  utils about initialization
 */
public interface LoginContract {

    interface LoginView extends BaseView {

        void loginData(String loginModel);

    }


    abstract class Presenter extends BasePresenter<LoginView> {

        public abstract void requsetLogin(String companyName ,String uName , String uPassword);

    }

}
