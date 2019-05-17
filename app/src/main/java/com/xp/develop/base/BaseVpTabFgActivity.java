package com.xp.develop.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xp.develop.R;
import com.xp.develop.customview.CustomViewPager;
import com.xp.develop.xpadapter.XpFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseVpTabFgActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private String [] mTitles;
    private XpFragmentPagerAdapter mAdapter;
    private CustomViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();


    protected abstract String[] Titles();
    protected abstract List<Fragment> Fragments(List<Fragment> mFragments);
    protected abstract Boolean isCanScroll();
    protected abstract void initTabLayout(TabLayout tabLayout);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tablayout_viewpage;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mTabLayout = findViewById(R.id.base_fragment_tab);
        mViewPager = findViewById(R.id.base_fragment_vp);

        //设置是否是可滑动的viewpage
        mViewPager.setScanScroll(isCanScroll());
        //tablayout的设置
        initTabLayout(mTabLayout);


        addTabToTabLayout();
        setupWithViewPager();

    }

    @Override
    protected boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    protected int isTemp() {
        return 0;
    }

    /**
     * Description：给TabLayout添加tab
     */
    private void addTabToTabLayout() {
        mTitles = Titles();
        for (int i = 0; i < mTitles.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles[i]));
        }
    }

    /**
     * Description：初始化FragmentPagerAdapter适配器并给ViewPager设置上该适配器，最后关联TabLayout和ViewPager
     */
    private void setupWithViewPager() {
        mFragments = Fragments(mFragments);
        mAdapter = new XpFragmentPagerAdapter(getSupportFragmentManager());
        mAdapter.addTitlesAndFragments(mTitles, mFragments);

        mViewPager.setAdapter(mAdapter); // 给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager); //关联TabLayout和ViewPager
    }
}
