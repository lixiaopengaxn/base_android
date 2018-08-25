package com.xp.develop.test.testmvp.persenter;

import android.content.Context;

import com.xp.develop.test.testmvp.contract.JinPingMeiContract;
import com.xp.develop.test.testmvp.entity.Testsinmp;
import com.xp.develop.test.testmvp.model.JinPingMeiModel;

import okhttp3.Call;

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
public class JinPingMeiPersenter extends JinPingMeiContract.Persenter {

    private JinPingMeiModel model;
    private Context context;

    public JinPingMeiPersenter(Context context) {
        this.model = new JinPingMeiModel();
        this.context = context;
    }

    @Override
    public void jinpingmei(String url) {


    }
}
