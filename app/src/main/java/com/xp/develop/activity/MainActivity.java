package com.xp.develop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;
import com.xp.develop.kotlin.KotlinActivity;
import com.xp.develop.test.activity.JinPingMeiActivity;
import com.xp.develop.test.activity.NewActivity;
import com.xp.develop.test.activity.TestActivity;
import com.xp.develop.test.activity.TestPingFenActivity;
import com.xp.develop.test.activity.TestScrollActivity;
import com.xp.develop.test.activity.TestTaskPhotoActivity;
import com.xp.develop.test.activity.TestWebViewActivity;
import com.xp.develop.utils.pulltoview.TwinklingRefreshLayout;
import com.xp.develop.utils.recycler.BaseQuickAdapter;
import com.xp.develop.utils.recycler.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/25
 * desc  :  utils about initialization
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.base_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.refresh)
    TwinklingRefreshLayout refreshLayout;

    private List<String> textTitle = new ArrayList<>();

    private List<Class> jumpClass = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.base_recycler_no_scroll_layout;
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
        titleView.setGoneLeftView();
//            uMPageName("主页");

            initData();

            initRecycler();
    }

    private void initRecycler() {
        HomeAdapter adapter = new HomeAdapter(textTitle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        refreshLayout.setPureScrollModeOn();

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position != 0){
                    openActivity(jumpClass.get(position - 1));
                } else {
                    openActivity(TestActivity.class);
                }
            }
        });
    }

    private void initData() {
        textTitle.add("网络请求");
        textTitle.add("图片加载");
        textTitle.add("webView");
        textTitle.add("评分");
        textTitle.add("选择图片");
        textTitle.add("滑动状态栏沉浸式");
        textTitle.add("newActivity");
        textTitle.add("MVP请求");
        textTitle.add("Kotlin");


        jumpClass.add(TestActivity.class);
        jumpClass.add(TestWebViewActivity.class);
        jumpClass.add(TestPingFenActivity.class);
        jumpClass.add(TestTaskPhotoActivity.class);
        jumpClass.add(TestScrollActivity.class);
        jumpClass.add(NewActivity.class);
        jumpClass.add(JinPingMeiActivity.class);
        jumpClass.add(KotlinActivity.class);
    }

    @Override
    public void onClick(View view) {

    }


    class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
        public HomeAdapter(@Nullable List<String> data) {
            super(R.layout.mian_title_item,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.text_view,item);

        }

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }


}
