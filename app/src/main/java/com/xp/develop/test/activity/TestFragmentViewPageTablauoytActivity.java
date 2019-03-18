package com.xp.develop.test.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.xp.develop.base.BaseVpTabFgActivity;
import com.xp.develop.fragment.FirstFragment;
import com.xp.develop.fragment.SecondFragment;
import com.xp.develop.fragment.ThirdFragment;

import java.util.List;

public class TestFragmentViewPageTablauoytActivity extends BaseVpTabFgActivity {
    @Override
    protected String[] Titles() {
        return new String[]{"FirstFragment","SecondFragment","ThirdFragment","FirstFragment","SecondFragment","ThirdFragment"};
    }

    @Override
    protected List<Fragment> Fragments(List<Fragment> mFragments) {
        mFragments.add(new FirstFragment());
        mFragments.add(new SecondFragment());
        mFragments.add(new ThirdFragment());
        mFragments.add(new FirstFragment());
        mFragments.add(new SecondFragment());
        mFragments.add(new ThirdFragment());
        return mFragments;
    }

    @Override
    protected Boolean isCanScroll() {
        return true;
    }

    @Override
    protected void initTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


}
