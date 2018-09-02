package com.xp.develop.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xp.develop.R;
import com.xp.develop.utils.statusView.DensityUtils;
import com.xp.develop.utils.statusView.Sofia;
import com.xp.develop.utils.statusView.TitleView;
import com.xp.develop.utils.swipe.SwipeBackActivityHelper;
import com.xp.develop.utils.swipe.SwipeBackLayout;

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
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends RxAppCompatActivity{


    private SwipeBackActivityHelper mHelper;

    //引用V层和P层
    private P presenter;
    private V view;

    protected P getPresenter() {
        return presenter;
    }

    protected Toolbar tooBar;

    //标题栏的view
    protected TitleView titleView;

    //子布局
    private FrameLayout mBodyContent;
    private int color;


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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        loadingChildLayout();
        //是否显示头布局
        isTempMethod();
        ButterKnife.bind(this);
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            view = createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(view);
        }

        initOnClick();
        if (IsSwipeBackPage()) {
            mHelper = new SwipeBackActivityHelper(this);
            mHelper.onActivityCreate();
            setSwipeBackEnable(true);
            getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        }



        /****
         * //标题栏左边默认的返回监听
         */
        titleView.setLeftTitleOnclickListener(view -> finish());

        titleView.setLeftTitleImgOnclickListener(view -> finish());


        init(savedInstanceState);
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
        //刘海
//
//        if(DeviceUtils.hasNotchInScreen(this) == true){
//            WindowManager.LayoutParams lp =getWindow().getAttributes();
//            lp.layoutInDisplayCutoutMode
//                    =WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT;
//            getWindow().setAttributes(lp);
//        }
//


        if (tooBar != null) {
            if (isTemp() == 1) {
                //全屏,且不显示状态栏
                tooBar.setVisibility(View.GONE);
                tooBar = null;

                //隐藏状态栏和底部虚拟按键
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    View decorView = getWindow().getDecorView();
                    decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                }
            } else if (isTemp() == 2) {
                //全屏，且显示状态栏
                tooBar.setVisibility(View.GONE);
                tooBar = null;
                Sofia.with(this)
                        .navigationBarBackground(ContextCompat.getColor(this, R.color.title_status_color))
                        .statusBarBackground(ContextCompat.getColor(this, R.color.title_status_color));
            } else if (isTemp() == 3) {

                //全屏,且显示透明状态栏
                tooBar.setVisibility(View.GONE);
                tooBar = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Android 5.0 以上 全透明
                    Window window = getWindow();
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                            | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    // 状态栏（以上几行代码必须，参考setStatusBarColor|setNavigationBarColor方法源码）
                    window.setStatusBarColor(ContextCompat.getColor(this, R.color.transparency_bar));
                    // 虚拟导航键
                    window.setNavigationBarColor(ContextCompat.getColor(this, R.color.transparency_bar));
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    // Android 4.4 以上 半透明
                    Window window = getWindow();
                    // 状态栏
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    // 虚拟导航键
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                }
            } else {
                tooBar.setVisibility(View.VISIBLE);
                Sofia.with(this)
                        .navigationBarBackground(ContextCompat.getColor(this, R.color.title_status_color))
                        .statusBarBackground(ContextCompat.getColor(this, R.color.title_status_color));
            }
        }


    }


    /***
     * 标题栏的颜色
     */
    protected void setTitleViewColor(int color) {
        this.color = color;
        if (tooBar != null) {
            tooBar.setBackgroundColor(ContextCompat.getColor(this, color));
            titleView.setTitleLayoutColor(ContextCompat.getColor(this, color));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null) {
            return mHelper.findViewById(id);
        }
        return v;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mHelper != null) {
            mHelper.onPostCreate();
        }
    }


    private void setSwipeBackEnable(boolean enable) {
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        if (swipeBackLayout != null) {
            swipeBackLayout.setEnableGesture(enable);
        }
    }

    private SwipeBackLayout getSwipeBackLayout() {
        if (mHelper == null) {
            return null;
        }
        return mHelper.getSwipeBackLayout();
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
