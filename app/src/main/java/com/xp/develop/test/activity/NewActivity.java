package com.xp.develop.test.activity;

import android.os.Bundle;
import android.view.View;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/7
 * desc  :  utils about initialization
 */
public class NewActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.new_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setStatusBarNanigationBarTooBarColor(R.color.red);
    }

    @Override
    protected void initOnClick() {

    }

    @Override
    protected boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    protected int isTemp() {
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

    @Override
    public void onClick(View view) {

    }
}
