package com.xp.develop.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xp.develop.utils.statusView.DensityUtils;
import com.xp.develop.utils.swipe.SwipeBackActivityHelper;
import com.xp.develop.utils.swipe.SwipeBackLayout;

import butterknife.ButterKnife;

/**
 * 作者：senon on 2017/12/27 10:09
 * 邮箱：a1083911695@163.com
 * 父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 */
public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends RxAppCompatActivity {


    private SwipeBackActivityHelper mHelper;

    //引用V层和P层
    private P presenter;
    private V view;
    
    public P getPresenter(){
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if(presenter == null){
            presenter = createPresenter();
        }
        if(view == null){
            view = createView();
        }
        if(presenter != null && view != null){
            presenter.attachView(view);
        }
        init(savedInstanceState);
    }

    //由子类指定具体类型
    public abstract int getLayoutId();
    public abstract P createPresenter();
    public abstract V createView();
    public abstract void init(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
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


    public boolean isShouldHideKeyboard(View v, MotionEvent event) {
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
    public void openActivity(Class<?> cls) {
        openActivity(cls, null);
    }

    /***
     * 启动带参数的Activity
     * @param cls
     * @param bundle
     */
    public void openActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void openActivityForResult(Class<?> cls, int requestCode) {
        openActivityForResult(cls, null, requestCode);
    }

    public void openActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }






}
