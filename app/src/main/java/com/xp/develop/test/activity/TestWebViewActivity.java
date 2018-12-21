package com.xp.develop.test.activity;

import android.os.Bundle;
import android.view.View;

import com.xp.develop.api.ApiConstants;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;
import com.xp.develop.base.BaseWebViewActivity;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/31
 * desc  :  utils about initialization
 */
public class TestWebViewActivity extends BaseWebViewActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        titleView.setRightText("分享");

        titleView.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


    }
    @Override
    protected String getUrl() {
        return ApiConstants.WEB_VIEW.WEB_GITLAB_URL;
    }

    @Override
    protected int type() {
        return 0;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected BaseView createView() {
        return null;
    }
}
