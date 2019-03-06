package com.xp.develop.utils.pop;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lxj.xpopup.animator.PopupAnimator;
import com.lxj.xpopup.core.CenterPopupView;
import com.xp.develop.R;

/**
 * @author : xp
 * @blog :  https://blog.csdn.net/qq_38729449
 * @create :  2019/3/5 15:53
 * @Describe :  utils about initialization
 */
public class LoadingView extends CenterPopupView {



    //自定义弹窗本质是一个自定义View，但是只需重写这个构造，其他的不用重写
    public LoadingView(@NonNull Context context) {
        super(context);
    }

    // 返回自定义弹窗的布局
    @Override
    protected int getImplLayoutId() {
        return R.layout.loading_layout;
    }
    // 执行初始化操作，比如：findView，设置点击，或者任何你弹窗内的业务逻辑
    @Override
    protected void onCreate() {
        super.onCreate();

//        findViewById(R.id.tv_close).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dismiss(); // 关闭弹窗
//            }
//        });
    }
    // 设置最大宽度，看需要而定
    @Override
    protected int getMaxWidth() {
        return super.getMaxWidth();
    }
    // 设置最大高度，看需要而定
    @Override
    protected int getMaxHeight() {
        return super.getMaxHeight();
    }
    // 设置自定义动画器，看需要而定
    @Override
    protected PopupAnimator getPopupAnimator() {
        return super.getPopupAnimator();
    }
}
