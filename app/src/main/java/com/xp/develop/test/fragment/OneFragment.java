package com.xp.develop.test.fragment;

import android.view.View;

import com.xp.develop.R;
import com.xp.develop.base.BaseFragment;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/7
 * desc  :  utils about initialization
 */
public class OneFragment extends BaseFragment {

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
    public void init(View view) {
//        uMPageName("OneFragment");
    }

    @Override
    protected void onLazyLoadOnce() {

    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }
}
