package com.xp.develop.utils.pop;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;

/**
 * @author : xp
 * @blog :  https://blog.csdn.net/qq_38729449
 * @create :  2019/3/5 13:37
 * @Describe :  utils about initialization
 * https://github.com/li-xiaojun/XPopup
 */
public class PopDialog {

    private static volatile PopDialog singleton;

    private PopDialog() {
    }

    public static PopDialog getInstance() {
        if (singleton == null) {
            synchronized (PopDialog.class) {
                if (singleton == null) {
                    singleton = new PopDialog();
                }
            }
        }
        return singleton;
    }

    /***
     * 显示确认和取消对话框
     * @param context
     * @param title
     * @param text
     * @param onConfirmListener
     */
    public void asConfirm(Context context, String title, String text, OnConfirmListener onConfirmListener) {
        XPopup.get(context).asConfirm(title, text, onConfirmListener)
                .show();
    }

    /***
     * 显示带输入框的确认和取消对话框
     * @param context
     * @param title
     * @param text
     * @param onInputConfirmListener
     */
    public void asInputConfirm(Context context, String title, String text, OnInputConfirmListener onInputConfirmListener) {
        XPopup.get(context).asInputConfirm(title, text, onInputConfirmListener)
                .show();
    }

    /***
     * 显示中间弹出的加载框
     * @param context
     * @param title
     */
    public void asLoading(Context context, String title) {
        XPopup.get(context).asLoading(title)
                .show();
    }

    /***
     * 关闭弹窗
     * @param context
     */
    public void dismiss(Context context) {
        XPopup.get(context).dismiss();
    }

    /***
     * 获取 XPopup 对象
     * @param context
     * @return
     */
    public XPopup getXp(Context context) {
        return XPopup.get(context);
    }

    /***
     * 在 view 的某处显示该对话框（内部会智能判断应该如何展示以及使用最佳的动画器）
     * @param context
     * @param atView
     * @param customPartShadowPopupView   弹窗里面的UI和逻辑需要你自己继承PartShadowPopupView来做
     */
    public void atView(Context context, View atView, CustomPartShadowPopupView customPartShadowPopupView) {
        XPopup.get(context)
                .asCustom(customPartShadowPopupView)
                .atView(atView)
                .show();
    }


    /***
     * ---------当你自定义弹窗的时候
     * ，需要选择继承CenterPopupView，
     * BottomPopupView，AttachPopupView，
     * DrawerPopupView，PartShadowPopupView
     * 其中之一。
     */

    /***
     * 自定义布局的网络请求的对话框
     * @param context
     * @param basePopupView
     */
    public void asCustom(Context context, LoadingView basePopupView) {
        XPopup.get(context)
                .hasShadowBg(false)
                .customAnimator(new AlphaAnimator())
                .asCustom(basePopupView)
                .dismissOnTouchOutside(false)
                .dismissOnBackPressed(false)
                .autoDismiss(false)
                .show();
    }

    /***
     * 自定义布局的对话框
     * 自选动画
     * @param context
     * @param basePopupView
     */
    public void asCustom(Context context, BasePopupView basePopupView, PopupAnimation popupAnimation) {
        XPopup.get(context)
                .asCustom(basePopupView)
                .popupAnimation(popupAnimation)
                .show();
    }

    /***    通用的对话框
     * 自定义布局通用的对话框的对话框
     * @param context
     * @param basePopupView
     */
    public void asCustomPopup(Context context, BasePopupView basePopupView) {
        XPopup.get(context)
                .asCustom(basePopupView)
                .show();
    }

    /***
     * 自定义布局的对话框
     * @param context
     * @param
     */
    public void asBootomList(Context context, String title, OnSelectListener onSelectListener, String... item) {
        XPopup.get(context).asBottomList(title, item, onSelectListener)
                .show();

    }

    public void asAtView(Context context, View atView, @DrawableRes int[] itemRes, OnSelectListener onSelectListener,PopupAnimation popupAnimation,String... item) {
        XPopup.get(context).asAttachList(item, itemRes, onSelectListener)
                .popupAnimation(popupAnimation)
                .atView(atView)  // 如果是要依附某个View，必须设置
                .show();
    }


}
