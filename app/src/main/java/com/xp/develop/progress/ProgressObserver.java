package com.xp.develop.progress;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.xp.develop.utils.ExceptionHandle;
import com.xp.develop.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 观察者
 */
public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {
    private static final String TAG = "okHttpExceptionInfo";
    private ObserverResponseListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private boolean isComplate;
    private Disposable disposable;

    public ProgressObserver(Context context, ObserverResponseListener listener, boolean isDialog, boolean cancelable) {
        this.listener = listener;
        this.context = context;
        if (isDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, cancelable);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.disposable = d;
        Log.e(TAG, "onSubscribe: ");
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        //可定制接口，通过code回调处理不同的业务
//        int code = ((BaseResponse) t).getCode();
//        String str = ((BaseResponse) t).getMsg();
//        if (code == 0) {
//            isComplate = true;
            listener.onNext(t);
//        } else {
//            isComplate = false;
//            //全局的请求成功但是code不正确的提示
//            ToastUtil.showLongToast(str);
//            listener.onSuccessCodeError(str);
//        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        //自定义异常处理
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            listener.onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            listener.onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }

        if (e instanceof UnknownHostException) {
            ToastUtil.showLongToast("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showLongToast("请求超时");
        } else if (e instanceof ConnectException) {
            ToastUtil.showLongToast("连接服务器失败");
        } else if (e instanceof HttpException) {
            ToastUtil.showLongToast("请求超时");
        } else {
            ToastUtil.showLongToast("请求失败");
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissProgressDialog();
            }
        },2000);
    }

    @Override
    public void onComplete() {
        if (isComplate) listener.onComplete();
        dismissProgressDialog();
        Log.e(TAG, "onComplete: ");
    }

    @Override
    public void onCancelProgress() {
        Log.e("requestCancel", "requestCancel: 我取消了");
        //如果处于订阅状态，则取消订阅
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
