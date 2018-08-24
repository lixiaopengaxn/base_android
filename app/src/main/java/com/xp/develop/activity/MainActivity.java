package com.xp.develop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseResponse;
import com.xp.develop.base.BaseView;
import com.xp.develop.entity.Login;
import com.xp.develop.model.LoginModel;
import com.xp.develop.progress.ObserverResponseListener;
import com.xp.develop.utils.ExceptionHandle;
import com.xp.develop.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    LoginModel<Object> loginModel;

    @BindView(R.id.text_view)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        loginModel = new LoginModel<>();


        HashMap<String, String> map = new HashMap<>();
        map.put("type", "yuantong");
        map.put("postid", "11111111111");
        getData(map, true, true);
    }

    @OnClick({R.id.text_view})
    protected void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view:
                ToastUtil.showLongToast("我是吐司");
                break;
        }
    }

    private void getData(HashMap<String, String> map, boolean b, boolean b1) {
        loginModel.login(this, map, b, b1, this.bindToLifecycle(), new ObserverResponseListener<BaseResponse<List<Login>>>() {
            @Override
            public void onNext(BaseResponse<List<Login>> listBaseResponse) {
                textView.setText(listBaseResponse.getData().toString());
                ToastUtil.showLongToast("我是吐司");
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }
        });
    }
}
