package com.xp.develop.mvp.model;

import android.content.Context;

import com.xp.develop.base.BaseModel;
import com.xp.develop.progress.ObserverResponseListener;

import io.reactivex.Observable;
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
 * time  :  2018/8/25
 * desc  :  utils about initialization
 */
public class OkHttpModel<T> extends BaseModel {

    public void okHttpPost(Context context,  Observable<T> observable, boolean isDialog, boolean cancelable,
                      ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {


        subscribe(context, observable, observerListener, transformer, isDialog, cancelable);
    }

    /***
     *
     * @param context 上下文
     * @param observable  retorfit的订阅
     * @param transformer 生命周期的绑定
     * @param observerListener 网络请求的回调
     */
    public void okHttp(Context context, Observable<T> observable, ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
        //当不需要指定是否由dialog时，可以调用这个方法
        subscribe(context, observable, observerListener, transformer);
    }
}
