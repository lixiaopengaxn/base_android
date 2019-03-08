package com.xp.develop.test.activity;

import android.os.Bundle;
import android.view.View;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.customview.CustomImageView;
import com.xp.develop.mvp.contract.LoginContract;
import com.xp.develop.mvp.entity.LoginModel;
import com.xp.develop.mvp.persenter.LoginPresenter;
import com.xp.develop.utils.ToastUtil;

import io.reactivex.ObservableTransformer;

/**
 * author :
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/7/20
 * desc  :  utils about initialization
 */
public class TestActivity extends BaseActivity<LoginContract.LoginView,LoginContract.Presenter> implements LoginContract.LoginView {

    public static String IMAGE_URL = "https://goss2.vcg.com/creative/vcg/400/version23/VCG41128618026.jpg";


    private CustomImageView image;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_layout;
    }


    @Override
    public void init(Bundle savedInstanceState) {

        image = (CustomImageView) findViewById(R.id.image);

        image.setImageViewUrl(IMAGE_URL);

    }

    @Override
    public void initOnClick() {

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /****
                 * 获取 P 层的对象   并且把数据 给 回调给 V  然后 这里实现的 V 的方法 就 被 执行了
                 */
                getPresenter().requsetLogin("beijing","admin","123456");
            }
        });

    }

    @Override
    public boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    public int isTemp() {
        return 0;
    }

    /****
     * 创建 P 层的对象   这里如果报红的话  注意一下自己的返回类型
     * @return
     */
    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    /***
     * 创建 V 层的对象   这里如果报红的话  注意一下自己的返回类型
     * @return
     */
    @Override
    public LoginContract.LoginView createView() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void loginData(LoginModel loginModel) {
        ToastUtil.showLongToast(loginModel.getResults().toString());
    }

    @Override
    public void onErrorMsg(String error) {

    }

    @Override
    public void onSuccessMsg(String success) {
        ToastUtil.showLongToast(success);
    }

    /***
     * 绑定网络请求和activity的生命周期
     * @param <T>
     * @return
     */
    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
