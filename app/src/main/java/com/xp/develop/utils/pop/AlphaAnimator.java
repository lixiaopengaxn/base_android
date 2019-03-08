package com.xp.develop.utils.pop;

import android.support.v4.view.animation.FastOutSlowInInterpolator;

import com.lxj.xpopup.animator.PopupAnimator;

/**
 * @author : xp
 * @blog :  https://blog.csdn.net/qq_38729449
 * @create :  2019/3/8 10:59
 * @Describe :  utils about initialization
 */
public class AlphaAnimator extends PopupAnimator {

    private final long animateDuration = 250;

    @Override
    public void initAnimator() {
        targetView.setAlpha((float) 0.5);
    }

    @Override
    public void animateShow() {
        targetView.animate().alpha(1).setInterpolator(new FastOutSlowInInterpolator()).setDuration(animateDuration).start();
    }

    @Override
    public void animateDismiss() {
        targetView.animate().alpha(0).setInterpolator(new FastOutSlowInInterpolator()).setDuration(animateDuration).start();
    }
}
