package com.xp.develop.test.activity;

import android.os.Bundle;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;
import com.xp.develop.customview.CustomImageView;

/**
 * author :
 * ---------------------------------------___           ___           ___         ___
 * ----------_____                       /  /\         /__/\         /__/|       /  /\
 * ---------/  /::\                     /  /::\        \  \:\       |  |:|      /  /:/
 * --------/  /:/\:\    ___     ___    /  /:/\:\        \  \:\      |  |:|     /__/::\
 * -------/  /:/~/::\  /__/\   /  /\  /  /:/~/::\   _____\__\:\   __|  |:|     \__\/\:\
 * ------/__/:/ /:/\:| \  \:\ /  /:/ /__/:/ /:/\:\ /__/::::::::\ /__/\_|:|____    \  \:\
 * ******\  \:\/:/~/:/  \  \:\  /:/  \  \:\/:/__\/ \  \:\~~\~~\/ \  \:\/:::::/     \__\:\
 * *******\  \::/ /:/    \  \:\/:/    \  \::/       \  \:\  ~~~   \  \::/~~~~      /  /:/
 * ********\  \:\/:/      \  \::/      \  \:\        \  \:\        \  \:\         /__/:/
 * *********\  \::/        \__\/        \  \:\        \  \:\        \  \:\        \__\/
 * **********\__\/                       \__\/         \__\/         \__\/
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/7/20
 * desc  :  utils about initialization
 */
public class TestActivity extends BaseActivity {

    public static String IMAGE_URL = "https://goss2.vcg.com/creative/vcg/400/version23/VCG41128618026.jpg";


    private CustomImageView image;

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_layout;
    }


    @Override
    public void init(Bundle savedInstanceState) {

        image = (CustomImageView) findViewById(R.id.image);

        image.setImageViewUrl(IMAGE_URL);
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
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

}
