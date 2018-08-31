package com.xp.develop.test.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xp.develop.R;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseTakePhotoActivity;
import com.xp.develop.base.BaseView;
import com.xp.develop.utils.takephoto.model.TResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/31
 * desc  :  utils about initialization
 */
public class TestTaskPhotoActivity extends BaseTakePhotoActivity {

    @BindView(R.id.test_recyerl)
    RecyclerView testRecyerl;

    private List<String> list = new ArrayList<>();

    @Override
    protected int getId() {
        return R.layout.test_task_photo_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        list.add("1");
    }

    public class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
        public HomeAdapter(int layoutResId, @Nullable List data) {
            super(R.layout.test_task_item_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

    @Override
    protected void initClick() {

    }


    @Override
    protected boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        photoResult();
    }

    /****
     * 拍照和选图返回来的路径
     */
    private void photoResult() {
        for(int i = 0; i < baseImages.size(); i++ ){
            list.add(baseImages.get(i).getOriginalPath() + "");
        }
//        adapter.setListAll(baseImages);

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected BaseView createView() {
        return null;
    }


    @OnClick(R.id.test_recyerl)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.test_recyerl:
                break;
        }
    }
}
