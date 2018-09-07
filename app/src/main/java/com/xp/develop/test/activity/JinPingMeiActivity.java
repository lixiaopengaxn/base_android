package com.xp.develop.test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xp.develop.R;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.test.testmvp.contract.JinPingMeiContract;
import com.xp.develop.test.testmvp.entity.Testsinmp;
import com.xp.develop.test.testmvp.persenter.JinPingMeiPersenter;
import com.xp.develop.utils.ToastUtil;


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
 * time  :  2018/8/22
 * desc  :  utils about initialization
 */
public class JinPingMeiActivity extends BaseActivity<JinPingMeiContract.View, JinPingMeiContract.Persenter> implements JinPingMeiContract.View {



    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        titleView.setCenterText("MVP");
    }

    @Override
    public void initOnClick() {
        String utl = "http://v.juhe.cn/historyWeather/province?key=8268580575e3e34aeeed29c34913d278";
        findViewById(R.id.text_view).setOnClickListener(v -> getPresenter().jinpingmei(utl));
    }

    @Override
    public boolean IsSwipeBackPage() {
        return false;
    }

    @Override
    public int isTemp() {
        return 0;
    }

    @Override
    public JinPingMeiContract.Persenter createPresenter() {
        return new JinPingMeiPersenter(this);
    }

    @Override
    public JinPingMeiContract.View createView() {
        return this;
    }



    @Override
    public void result(Testsinmp data) {
        ((TextView)(findViewById(R.id.text_view))).setText(data.toString());
    }

    @Override
    public void onErrorMsg(String error) {

    }

    @Override
    public void onSuccessMsg(String success) {

    }

    @Override
    public void onClick(View view) {

    }
}
