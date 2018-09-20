package com.xp.develop.progress;


import com.xp.develop.utils.ExceptionHandle;

/**
 * 请求监听
 */

public interface ObserverResponseListener<T> {
    /**
     * 响应成功
     * @param t
     */
    void onNext(T t);

    /**
     * 响应失败
     * @param e
     */
    void onError(ExceptionHandle.ResponeThrowable e);

    /***
     * 参数错误
     */
    void onCodeError(String errorMsg);

    /***
     * 请求已完成
     */
    void onComplete();
}
