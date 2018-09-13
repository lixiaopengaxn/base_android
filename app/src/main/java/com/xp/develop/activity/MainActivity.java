package com.xp.develop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseResponse;
import com.xp.develop.base.BaseView;
import com.xp.develop.entity.Login;
import com.xp.develop.kotlin.KotlinActivity;
import com.xp.develop.model.LoginModel;
import com.xp.develop.progress.ObserverResponseListener;
import com.xp.develop.test.activity.JinPingMeiActivity;
import com.xp.develop.test.activity.NewActivity;
import com.xp.develop.test.activity.TestActivity;
import com.xp.develop.test.activity.TestPingFenActivity;
import com.xp.develop.test.activity.TestScrollActivity;
import com.xp.develop.test.activity.TestTaskPhotoActivity;
import com.xp.develop.test.activity.TestWebViewActivity;
import com.xp.develop.utils.ExceptionHandle;
import com.xp.develop.utils.recycler.BaseQuickAdapter;
import com.xp.develop.utils.recycler.BaseViewHolder;

import java.util.ArrayList;
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

    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;

    private List<String> textTitle = new ArrayList<>();

    private List<Class> jumpClass = new ArrayList<>();

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

            initData();

            initRecycler();
    }

    private void initRecycler() {
        HomeAdapter adapter = new HomeAdapter(textTitle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position != 0){
                    openActivity(jumpClass.get(position - 1));
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
//
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.text_view:
//                HashMap<String, String> map = new HashMap<>();
//                map.put("type", "yuantong");
//                map.put("postid", "11111111111");
////                getData(map, true, true);
//                break;
//            case R.id.text_view_1:
//                openActivity(TestActivity.class);
//                break;
//            case R.id.text_view_2:
//                openActivity(TestWebViewActivity.class);
//                break;
//
//            case R.id.text_view_3:
//                openActivity(TestPingFenActivity.class);
//                break;
//
//            case R.id.text_view_4:
//                openActivity(TestTaskPhotoActivity.class);
//                break;
//
//            case R.id.text_view_5:
//                openActivity(TestScrollActivity.class);
//                break;
//            case R.id.text_view_6:
//                openActivity(NewActivity.class);
//                break;
//            case R.id.text_view_7:
//                openActivity(JinPingMeiActivity.class);
//                break;
//            case R.id.text_view_8:
//                openActivity(KotlinActivity.class);
//                break;
//        }
//    }

//    private void getData(HashMap<String, String> map, boolean b, boolean b1) {
//        loginModel.login(this, map, b, b1, this.bindToLifecycle(), new ObserverResponseListener<BaseResponse<List<Login>>>() {
//            @Override
//            public void onNext(BaseResponse<List<Login>> listBaseResponse) {
//                textView.setText(listBaseResponse.getData().toString());
//                textView.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.white));
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//
//            }
//        });
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keyCode, event);
    }
}
