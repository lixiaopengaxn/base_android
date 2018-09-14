package com.xp.develop.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xp.develop.R;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseTakePhotoActivity;
import com.xp.develop.base.BaseView;
import com.xp.develop.utils.ToastUtil;
import com.xp.develop.utils.pop.SlideFromBottomPopup;
import com.xp.develop.utils.recycler.BaseQuickAdapter;
import com.xp.develop.utils.recycler.BaseViewHolder;
import com.xp.develop.utils.takephoto.model.TResult;
import com.xp.develop.utils.takephoto.permission.PermissionManager;

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

    private HomeAdapter homeAdapter;

    private List<String> list = new ArrayList<>();
    private SlideFromBottomPopup slidePopup;

    @Override
    protected int getId() {
        return R.layout.test_task_photo_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        list.add("1");
        homeAdapter = new HomeAdapter(list);
        testRecyerl.setHasFixedSize(true);
        testRecyerl.setLayoutManager(new GridLayoutManager(this,3));

        testRecyerl.setAdapter(homeAdapter);


        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                getUrl();
                configCompress(takePhoto);
                configTakePhotoOption(takePhoto);
                showDialog();
            }
        });
    }

    /****
     * 添加图片的对话框
     */
    private void showDialog() {
        slidePopup = new SlideFromBottomPopup(this) {
            @Override
            protected void getPopView(View view) {
                view.findViewById(R.id.pop_evaluate_camera).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = 9 - homeAdapter.getData().size();
                        if(i > 0){
                            //裁剪
//                            takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                            //不裁剪
                            takePhoto.onPickFromCapture(imageUri);
                        } else {
                            ToastUtil.showShortToast("最多添加9张张片");
                            slidePopup.dismiss();
                        }

                    }
                });
                view.findViewById(R.id.pop_evaluate_photo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = 9 - homeAdapter.getData().size();
                        if (i > 0) {
                            //裁剪
//                            takePhoto.onPickMultipleWithCrop(i, getCropOptions());
                            //不裁剪
                            takePhoto.onPickMultiple(i);
                            return;
                        }
                        if(i > 0){
                            //裁剪
//                            takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
                            //不裁剪
                            takePhoto.onPickFromGallery();
                        } else {
                            ToastUtil.showShortToast("最多显示9张张片");
                            slidePopup.dismiss();
                        }

                    }
                });
            }

            @Override
            protected int getLayoutId() {
                return R.layout.popup_evaluate_layout;
            }
        };
        slidePopup.setBlurBackgroundEnable(true).showPopupWindow();
    }

    public class HomeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
        public HomeAdapter(@Nullable List data) {
            super(R.layout.test_task_item_layout, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView imageView = helper.getView(R.id.test_task_item_image);
            Glide.with(TestTaskPhotoActivity.this).asBitmap().load(item).into(imageView);
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
        baseImages = result.getImages();
        photoResult();
        slidePopup.dismiss();
    }

    /****
     * 拍照和选图返回来的路径
     */
    private void photoResult() {
        for(int i = 0; i < baseImages.size(); i++ ){
            list.add(baseImages.get(i).getOriginalPath() + "");
        }
        homeAdapter.replaceData(list);

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
