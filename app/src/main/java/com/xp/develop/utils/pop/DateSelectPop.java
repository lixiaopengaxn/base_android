package com.xp.develop.utils.pop;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.xp.develop.utils.pop.basepopup.BasePopupWindow;
import com.xp.develop.utils.pop.util.DimensUtils;

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
 * time  :  2018/8/13
 * desc  :  utils about initialization
 */
public abstract class DateSelectPop extends BasePopupWindow implements View.OnClickListener {
    private View popupView;


    public DateSelectPop(Activity context) {
        super(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setAutoLocatePopup(true);
        bindEvent();
    }

    private void bindEvent() {

        if (popupView != null) {


        }
    }
//    @Override
//    protected Animation initShowAnimation() {
//        return getTranslateVerticalAnimation(1f, 0, 350);
//    }
//
//    @Override
//    protected Animation initExitAnimation() {
//        return getTranslateVerticalAnimation(0, 1f, 350);
//    }

    @Override
    protected Animation initShowAnimation() {
        TranslateAnimation translateAnimation = null;
        if(getAnimao().equals("top")){
            translateAnimation = new TranslateAnimation(0f, 0f, -DimensUtils.dipToPx(getContext(), 350f), 0);
            translateAnimation.setDuration(450);
            translateAnimation.setInterpolator(new OvershootInterpolator(1));
        } else if(getAnimao().equals("button")){
            return getTranslateVerticalAnimation(1f, 0, 200);
        }

        return translateAnimation;
    }

    @Override
    protected Animation initExitAnimation() {
        TranslateAnimation translateAnimation = null;
        if(getAnimao().equals("top")) {
            translateAnimation = new TranslateAnimation(0f, 0f, 0, -DimensUtils.dipToPx(getContext(), 350f));
            translateAnimation.setDuration(450);
            translateAnimation.setInterpolator(new OvershootInterpolator(-4));
        } else if(getAnimao().equals("button")){
            return getTranslateVerticalAnimation(0, 1f, 200);
        }
        return translateAnimation;
    }


    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(getIdLayout(), null);
        getPopView(popupView);
        return popupView;
    }

    public abstract int getIdLayout();

    public abstract int getIdAnimao();

    public abstract void getPopView(View view);

    public abstract String getAnimao();

    @Override
    public View initAnimaView() {
        return popupView.findViewById(getIdAnimao());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }
}
