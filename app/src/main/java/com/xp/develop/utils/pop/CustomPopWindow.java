package com.xp.develop.utils.pop;


import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

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
 * time  :  2018/7/21
 * desc  :  utils about initialization
 */
public class CustomPopWindow extends PopupWindow {
    // 声明 popwindow控制器
    PopupController pController;

    // private的构造函数 避免外部调用
    private CustomPopWindow(Context context) {
        if(pController != null){
            dismiss();
            pController = new PopupController(context, this);
        } else {
            pController = new PopupController(context, this);
        }
    }

    @Override
    public int getWidth() {
        return pController.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return pController.mPopupView.getMeasuredHeight();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        pController.setBackGroundLevel(1.0f);
    }

    // 提供 popwindow内部控件操作的接口
    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    // 建造者模式 通过Builder 暴露方法给外部链式调用   并获取相关参数
    public static class Builder {
        // 声明 popwindow控制器的参数类 用于 外部赋予各种参数给 控制器 去 呈现不同的效果
        private PopupController.PopupParams params;
        // 声明 接口
        private ViewInterface listener;
        // 构造方法 创建 参数类对象
        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID形式
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局 view形式
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置子View 的监听器
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content （popwindow必须设置 而这里可以通过参数类自己赋一个初值为wrapConent）
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;   // 设置背景 因为要设置点击外部消失popwindow要设置背景
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击外部 popwindow消失  注意这里只是popwindow消失与否 并不是能否点击外部与否 都是能够点击外部的点击事件的
         *
         * @param touchable 是否点击消失
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }

        // 设置完 以上各种属性 则创建popwindow
        public CustomPopWindow create() {
            CustomPopWindow popupWindow = new CustomPopWindow(params.mContext);
            params.apply(popupWindow.pController);
            if (listener != null && params.layoutResId != 0) {

                listener.getChildView(popupWindow.pController.mPopupView, params.layoutResId);
            }
//             ViewUtil.measureWidthAndHeight(popupWindow.pController.mPopupView);
            return popupWindow;
        }
    }
}
