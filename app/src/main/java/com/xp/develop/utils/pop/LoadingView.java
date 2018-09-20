package com.xp.develop.utils.pop;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.xp.develop.R;
import com.xp.develop.utils.pop.basepopup.BasePopupWindow;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/18
 * desc  :  utils about initialization
 */
public class LoadingView extends BasePopupWindow {

    public LoadingView(Context context) {
        super(context);
    }

    @Override
    protected Animation initShowAnimation() {
        return getDefaultAlphaAnimation();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.loading_layout);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anim);
    }
}
