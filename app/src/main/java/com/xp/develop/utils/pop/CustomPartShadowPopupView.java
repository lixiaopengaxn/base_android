package com.xp.develop.utils.pop;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.xp.develop.R;

/**
 * @author : xp
 * @blog :  https://blog.csdn.net/qq_38729449
 * @create :  2019/3/5 15:44
 * @Describe :  utils about initialization
 */
public class CustomPartShadowPopupView extends PartShadowPopupView {
    public CustomPartShadowPopupView(@NonNull Context context) {
        super(context);
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.sofia_host_layout; // 编写你自己的布局
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        // 实现一些UI的初始和逻辑处理
    }
}
