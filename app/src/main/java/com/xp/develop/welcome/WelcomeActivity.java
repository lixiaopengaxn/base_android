package com.xp.develop.welcome;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.xp.develop.R;
import com.xp.develop.activity.MainActivity;
import com.xp.develop.base.BaseActivity;
import com.xp.develop.base.BasePresenter;
import com.xp.develop.base.BaseView;

/**
 * author : xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/25
 * desc  :  utils about initialization
 */
public class WelcomeActivity  extends BaseActivity{

    public static final long DOWN_TIMER_MILLIS = 800;
    public static final long DOWN_TIMER_TIME = 1000;
    private MCountDownTimer mDownTimer = new MCountDownTimer(DOWN_TIMER_TIME, DOWN_TIMER_MILLIS);

    private class MCountDownTimer extends CountDownTimer {
        public MCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            onCheckComplete();
        }
    }

    private void onCheckComplete() {
        openActivity(MainActivity.class);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welecome;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        View decorView = getWindow().getDecorView();
        decorView.setAlpha(0.0f);
        ObjectAnimator animator = ObjectAnimator.ofFloat(decorView, "alpha", 0.0f, 1.0f);
        animator.setDuration(100L);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
        mDownTimer.start();
    }

    @Override
    public void initOnClick() {

    }

    @Override
    public boolean IsSwipeBackPage() {
        return false;
    }

    @Override
    public int isTemp() {
        return 1;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }
}
