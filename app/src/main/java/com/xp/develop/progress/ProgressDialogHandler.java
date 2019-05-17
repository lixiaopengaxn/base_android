package com.xp.develop.progress;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.xp.develop.utils.pop.LoadingView;
import com.xp.develop.utils.pop.PopDialog;


/**
 * Dialog的进度控制
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private Context context;
    //取消对话框
    private boolean cancelable;
    //取消请求
    private ProgressCancelListener mProgressCancelListener;

    private LoadingView loadingView;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {

        if (loadingView == null) {
            loadingView = new LoadingView(context);
        }


        if (cancelable) {
            PopDialog.getInstance().asCustom(context, loadingView);
        } else {
            PopDialog.getInstance() .asCustom(context, loadingView);
        }



    }

    private void dismissProgressDialog() {

        if (loadingView != null) {
            loadingView.dismiss();
            mProgressCancelListener.onCancelProgress();
        } else {
            mProgressCancelListener.onCancelProgress();
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }
}
