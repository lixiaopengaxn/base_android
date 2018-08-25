package com.xp.develop.test.testmvp.model;


import android.content.Context;

import com.xp.develop.api.Api;
import com.xp.develop.base.BaseModel;
import com.xp.develop.progress.ObserverResponseListener;

import org.json.JSONObject;

import java.util.HashMap;

import io.reactivex.ObservableTransformer;

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
public class JinPingMeiModel<T> extends BaseModel {

    public void login(Context context, HashMap<String,String> map, boolean isDialog, boolean cancelable,
                      ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){

        //当不需要指定是否由dialog时，可以调用这个方法
//        subscribe(context, Api.getApiService().login(map), observerListener);
        subscribe(context, Api.getApiService().login(map), observerListener,transformer,isDialog,cancelable);
    }


    public void logout(Context context, HashMap<String,String> map, boolean isDialog, boolean cancelable,
                       ObservableTransformer<T,T> transformer, ObserverResponseListener observerListener){

        subscribe(context, Api.getApiService().logout(map), observerListener,transformer,isDialog,cancelable);
    }

}
