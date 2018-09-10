package com.xp.develop.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xp.develop.R;
import com.xp.develop.utils.SingleUtils;
import com.xp.develop.utils.statusView.DensityUtils;
import com.xp.develop.utils.statusView.Sofia;
import com.xp.develop.utils.statusView.TitleView;
import com.xp.develop.utils.statusView.StatusbarUtils;
import com.xp.develop.utils.swipe.BGASwipeBackHelper;

import butterknife.ButterKnife;

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
 * time  :  2018/8/25
 * desc  :  父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends RxAppCompatActivity implements  BGASwipeBackHelper.Delegate, View.OnClickListener{


    protected BGASwipeBackHelper mSwipeBackHelper;

    //引用V层和P层
    protected P mvpPresenter;
    private V mvpView;

    protected P getPresenter() {
        return mvpPresenter;
    }

    protected Toolbar tooBar;

    //标题栏的view
    protected TitleView titleView;

    //子布局
    private FrameLayout mBodyContent;


    /**
     * 获取加载页面的资源id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 初始化点击事件
     */
    protected abstract void initOnClick();

    /***
     * TODO
     * 是否侧滑退出
     */
    protected abstract boolean IsSwipeBackPage();

    /**
     * 是否加载标题模板
     *
     * @return
     */
    protected abstract int isTemp();

    /***
     * 由子类指定具体类型
     * @return
     */
    protected abstract P createPresenter();

    protected abstract V createView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        loadingChildLayout();
        //是否显示头布局
        isTempMethod();
        ButterKnife.bind(this);
        presenter();



        /****
         * //标题栏左边默认的返回监听
         */
        titleView.setLeftTitleOnclickListener(view -> finish());

        titleView.setLeftTitleImgOnclickListener(view -> finish());


        init(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initOnClick();
    }

    /***
     * 创建 presenter 和 view
     */
    private void presenter(){

        if (mvpPresenter == null) {
            mvpPresenter = createPresenter();
        }
        if (mvpView == null) {
            mvpView = createView();
        }
        if (mvpPresenter != null && mvpView != null) {
            mvpPresenter.attachView(mvpView);
        }
    }


    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

//        // 设置滑动返回是否可用。默认值为 true
//        mSwipeBackHelper.setSwipeBackEnable(IsSwipeBackPage());
//        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
//        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
//        // 设置是否是微信滑动返回样式。默认值为 true
//        mSwipeBackHelper.setIsWeChatStyle(true);
//        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.shadow_left);
//        // 设置是否显示滑动返回的阴影效果。默认值为 true
//        mSwipeBackHelper.setIsNeedShowShadow(true);
//        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
//        mSwipeBackHelper.setIsShadowAlphaGradient(true);
//        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
//        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
//        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(true);
    }


    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return IsSwipeBackPage();
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {

    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
    }


    /***
     * 初始化子布局
     */
    private void loadingChildLayout() {
        titleView = (TitleView) findViewById(R.id.title_view);
        tooBar = (Toolbar) findViewById(R.id.toolbar);
        mBodyContent = (FrameLayout) findViewById(R.id.base_child_layout);
        View view = LayoutInflater.from(this).inflate(getLayoutId(), null, false);
        if (mBodyContent != null) {
            mBodyContent.removeAllViews();
            mBodyContent.addView(view);
        }
    }


    /***
     * 是否显示 toobar
     * isTemp() == 1  //全屏,且不显示状态栏
     * isTemp() == 2  //全屏，且显示状态栏
     * isTemp() == else  显示状态栏和title
     */
    @SuppressLint("ResourceAsColor")
    public void isTempMethod() {
        if (tooBar != null) {
            if (isTemp() == 1) {
                //全屏,且不显示状态栏
                tooBar.setVisibility(View.GONE);
                tooBar = null;
                StatusbarUtils.hideToobar(this);
            } else if (isTemp() == 2) {
                //全屏，且显示状态栏
                tooBar.setVisibility(View.GONE);
                tooBar = null;
                setStatusBarNanigationBarColor(R.color.colorPrimary);
            } else if (isTemp() == 3) {

                //全屏,且显示透明状态栏
                tooBar.setVisibility(View.GONE);
                tooBar = null;
                StatusbarUtils.enableTranslucentStatusbar(this);
            } else {
                tooBar.setVisibility(View.VISIBLE);
                setStatusBarNanigationBarTooBarColor(R.color.colorPrimary);
            }
        }

    }


    /***
     * 标题栏的颜色
     */
    protected void setTooBarColor(@ColorRes int color) {
        if (tooBar != null) {
            tooBar.setBackgroundColor(ContextCompat.getColor(this, color));
        }
    }

    /**
     * setTempColor 标题栏，状态栏，navigationBar
     */
    protected void setStatusBarNanigationBarTooBarColor(@ColorRes int color){
        setTooBarColor(color);
        setNanigationBarColor(color);
        setStatusBarColor(color);
    }

    /****
     * 单独设置navigationbar
     * 的颜色
     */
    protected void setNanigationBarColor(@ColorRes int color){
        Sofia.with(this)
                .navigationBarBackground(ContextCompat.getColor(this, color));
    }

    /****
     * 单独设置navigationbar
     * 的颜色
     */
    protected void setStatusBarColor(@ColorRes int color){
        Sofia.with(this).
        statusBarBackground(ContextCompat.getColor(this,color));
    }

    /**
     * setTempColor 标题栏，状态栏
     */
    protected void setStatusBarNanigationBarColor(@ColorRes int color){
        setTooBarColor(color);
        setStatusBarColor(color);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        if(mSwipeBackHelper != null){
            mSwipeBackHelper = null;
        }
        if(tooBar != null || titleView != null){
            tooBar = null;
            titleView = null;
        }
        if(mBodyContent != null){
            mBodyContent = null;
        }
    }


    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        //必不可少，否则所有的组件都不会有TouchEvent
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] loc = {0, 0};
            v.getLocationInWindow(loc);
            int left = loc[0];
            int top = loc[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            int width = DensityUtils.screenWidth(this);
            if (event.getX() > left && event.getX() < width &&
                    event.getY() > top && event.getY() < bottom) {
                //点击的是输入框区域
                return false;
            } else {
                ((EditText) v).clearFocus();
                return true;
            }
        }
        return false;
    }

    /***
     *弹出软键盘
     */
    protected void inputSoftKey() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /***
     *关闭软键盘
     */
    protected void shutSoftKey() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /**
     * 启动不带参数的Activity
     *
     * @param cls
     */
    protected void openActivity(Class<?> cls) {
        openActivity(cls, null);
    }

    /***
     * 启动带参数的Activity
     * @param cls
     * @param bundle
     */
    protected void openActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void openActivityForResult(Class<?> cls, int requestCode) {
        openActivityForResult(cls, null, requestCode);
    }

    protected void openActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }




    /**
     * 是否按照宽度进行等比例适配 (为了保证在高宽比不同的屏幕上也能正常适配, 所以只能在宽度和高度之中选一个作为基准进行适配)
     *
     * @return {@code true} 为按照宽度适配, {@code false} 为按照高度适配
     */
//    @Override
//    public boolean isBaseOnWidth() {
//        return true;
//    }
//
//    /**
//     * 这里使用 IPhone 的设计图, IPhone 的设计图尺寸为 750px * 1334px, 因为这个页面使用副单位进行布局
//     * 所以可以直接以像素作为单位返回设计图的尺寸
//     * <p>
//     * 返回设计图上的设计尺寸
//     * {@link #getSizeInDp} 须配合 {@link #isBaseOnWidth()} 使用, 规则如下:
//     * 如果 {@link #isBaseOnWidth()} 返回 {@code true}, {@link #getSizeInDp} 则应该返回设计图的总宽度
//     * 如果 {@link #isBaseOnWidth()} 返回 {@code false}, {@link #getSizeInDp} 则应该返回设计图的总高度
//     * 如果您不需要自定义设计图上的设计尺寸, 想继续使用在 AndroidManifest 中填写的设计图尺寸, {@link #getSizeInDp} 则返回 {@code 0}
//     *
//     * @return 设计图上的设计尺寸
//     */
//    @Override
//    public float getSizeInDp() {
//        return ApiConstants.AUTO_SIZE.DP;
//    }
}
