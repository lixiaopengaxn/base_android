package com.xp.develop.progress;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.xp.develop.utils.pop.LoadingView;
import com.xp.develop.utils.pop.PopupController;


/**
 * Dialog的进度控制
 */

public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private LoadingView sad;
    private Context context;
    //取消对话框
    private boolean cancelable;
    //取消请求
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    private void initProgressDialog() {
        if (sad == null) {
            sad = new LoadingView(context);

            if (!sad.isShowing()) {

                sad.showPopupWindow();
                sad.setDismissWhenTouchOutside(false);
            }
        }
    }

    private void dismissProgressDialog() {
        if (sad != null) {
            sad.dismiss();
            sad = null;
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
