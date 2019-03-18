package com.xp.develop.fragment;

import android.view.View;

import com.xp.develop.R;
import com.xp.develop.base.BaseFragment;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;

public class SecondFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.base_child_frame_layout;
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

    }

    @Override
    protected void onSoleLoadOnce() {

    }

    @Override
    protected void onVisibleToUser() {

    }

    @Override
    protected void onInvisibleToUser() {

    }
}
