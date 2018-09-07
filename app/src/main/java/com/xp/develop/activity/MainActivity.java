package com.xp.develop.activity;

import android.os.Bundle;
import android.view.KeyEvent;
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
import com.xp.develop.test.activity.TestActivity;
import com.xp.develop.test.activity.TestPingFenActivity;
import com.xp.develop.test.activity.TestScrollActivity;
import com.xp.develop.test.activity.TestTaskPhotoActivity;
import com.xp.develop.test.activity.TestWebViewActivity;
import com.xp.develop.utils.ExceptionHandle;
import com.xp.develop.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/25
 * desc  :  utils about initialization
 */

public class MainActivity extends BaseActivity {

    LoginModel<Object> loginModel;

    @BindView(R.id.text_view)
    TextView textView;

    @BindView(R.id.text_view_1)
    TextView textView1;

    @BindView(R.id.text_view_2)
    TextView textView2;

    @BindView(R.id.text_view_3)
    TextView textView3;

    @BindView(R.id.text_view_4)
    TextView textView4;

    @BindView(R.id.text_view_5)
    TextView textView5;

    @BindView(R.id.text_view_6)
    TextView textView6;

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
        titleView.getLeftView().setVisibility(View.GONE);
        loginModel = new LoginModel<>();

    }

    @Override
    public void initOnClick() {

    }

    @Override
    public boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    public int isTemp() {
        return 0;
    }

    @OnClick({R.id.text_view,R.id.text_view_1,R.id.text_view_2,R.id.text_view_3,R.id.text_view_4,R.id.text_view_5,R.id.text_view_6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view:
                HashMap<String, String> map = new HashMap<>();
                map.put("type", "yuantong");
                map.put("postid", "11111111111");
                getData(map, true, true);
                break;
            case R.id.text_view_1:
                openActivity(TestActivity.class);
                break;
            case R.id.text_view_2:
                openActivity(TestWebViewActivity.class);
                break;

            case R.id.text_view_3:
                openActivity(TestPingFenActivity.class);
                break;

            case R.id.text_view_4:
                openActivity(TestTaskPhotoActivity.class);
                break;

            case R.id.text_view_5:
                openActivity(TestScrollActivity.class);
                break;
        }
    }

    private void getData(HashMap<String, String> map, boolean b, boolean b1) {
        loginModel.login(this, map, b, b1, this.bindToLifecycle(), new ObserverResponseListener<BaseResponse<List<Login>>>() {
            @Override
            public void onNext(BaseResponse<List<Login>> listBaseResponse) {
//                textView.setText(listBaseResponse.getData().toString());
                ToastUtil.showLongToast(listBaseResponse.getData().toString());
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
