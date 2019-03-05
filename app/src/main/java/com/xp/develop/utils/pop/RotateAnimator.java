package com.xp.develop.utils.pop;

import android.support.v4.view.animation.FastOutSlowInInterpolator;

import com.lxj.xpopup.animator.PopupAnimator;

/**
 * @author : xp
 * @blog :  https://blog.csdn.net/qq_38729449
 * @create :  2019/3/5 15:50
 * @Describe :  旋转动画
 */
public class RotateAnimator extends PopupAnimator {

    private final long animateDuration = 1000;


    @Override
    public void initAnimator() {
        targetView.setScaleX(0);
        targetView.setScaleY(0);
        targetView.setAlpha(0);
        targetView.setRotation(360);
    }
    @Override
    public void animateShow() {

        targetView.animate().rotation(0).scaleX(1).scaleY(1).alpha(1).setInterpolator(new FastOutSlowInInterpolator()).setDuration(animateDuration).start();
    }
    @Override
    public void animateDismiss() {
        targetView.animate().rotation(360).scaleX(0).scaleY(0).alpha(0).setInterpolator(new FastOutSlowInInterpolator()).setDuration(animateDuration).start();
    }
}
