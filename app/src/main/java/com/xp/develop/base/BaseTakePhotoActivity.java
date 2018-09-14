package com.xp.develop.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;


import com.xp.develop.utils.takephoto.app.TakePhoto;
import com.xp.develop.utils.takephoto.app.TakePhotoImpl;
import com.xp.develop.utils.takephoto.compress.CompressConfig;
import com.xp.develop.utils.takephoto.model.CropOptions;
import com.xp.develop.utils.takephoto.model.InvokeParam;
import com.xp.develop.utils.takephoto.model.TContextWrap;
import com.xp.develop.utils.takephoto.model.TImage;
import com.xp.develop.utils.takephoto.model.TResult;
import com.xp.develop.utils.takephoto.model.TakePhotoOptions;
import com.xp.develop.utils.takephoto.permission.InvokeListener;
import com.xp.develop.utils.takephoto.permission.PermissionManager;
import com.xp.develop.utils.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.ArrayList;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/31
 * desc  :  utils about initialization
 */
public abstract class BaseTakePhotoActivity extends BaseActivity implements TakePhoto.TakeResultListener,InvokeListener {

    private InvokeParam invokeParam;
    protected TakePhoto takePhoto;
    protected TakePhoto basePhoto;
    protected ArrayList<TImage> baseImages;
    protected Uri imageUri;

    @Override
    protected int getLayoutId() {
        return getId();
    }

    protected abstract int getId();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initClick();

    @Override
    protected void init(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        initView(savedInstanceState);
    }

    @Override
    protected void initOnClick() {
        initClick();
    }

    @Override
    protected boolean IsSwipeBackPage() {
        return true;
    }

    @Override
    public int isTemp() {
        return 0;
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    /** * 获取TakePhoto实例 * @return */
    public TakePhoto getTakePhoto(){
        if (takePhoto ==null){
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    /***
     * 自带的相册
     * @param takePhoto
     */
    protected void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        builder.setCorrectImage(true);
        takePhoto.setTakePhotoOptions(builder.create());

    }

    /***
     * 压缩文件
     * @param takePhoto
     */
    protected void configCompress(TakePhoto takePhoto) {
        CompressConfig config = new CompressConfig.Builder().setMaxSize(102400)
                .setMaxPixel(800 >= 800 ? 800 : 800)
                .enableReserveRaw(true)
                .create();
        takePhoto.onEnableCompress(config, false);
//        takePhoto.onEnableCompress(null, false);
    }

    /***
     * 裁剪的尺寸
     * @return
     */
    protected CropOptions getCropOptions() {
        int height = 800;
        int width = 800;
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(true);
        return builder.create();
    }

    /***
     * 初始sd的路径
     */
    protected void getUrl(){
        basePhoto = getTakePhoto();
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        imageUri = Uri.fromFile(file);
    }


    @Override
    public void takeSuccess(TResult result) {

    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }
}
