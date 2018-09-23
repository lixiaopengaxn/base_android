package com.xp.develop.base;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.xp.develop.R;
import com.xp.develop.api.ApiConstants;
import com.xp.develop.utils.SingleUtils;
import com.xp.develop.utils.swipe.BGASwipeBackHelper;


/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/1
 * desc  :  utils about initialization
 */
public class BaseApplication extends Application {

    private static Context mContext;//全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        //对单位的自定义配置, 请在 App 启动时完成
//        configUnits();


        /**
         * 必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回
         * 第一个参数：应用程序上下文
         * 第二个参数：如果发现滑动返回后立即触摸界面时应用崩溃，请把该界面里比较特殊的 View 的 class 添加到该集合中，目前在库中已经添加了 WebView 和 SurfaceView
         */
        BGASwipeBackHelper.init(this, null);


        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);


        /*
         * 注意：如果您已经在AndroidManifest.xml中配置过appkey和channel值，可以调用此版本初始化函数。
         */
        UMConfigure.init(mContext,UMConfigure.DEVICE_TYPE_PHONE, ApiConstants.UM.UM_KEY);

        //场景类型设置接口
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);

        //secretkey设置接口，防止AppKey被盗用   需要企业认证
        MobclickAgent.setSecret(mContext, "");

        // 禁止默认的页面统计功能，这样将不会再自动统计Activity页面。
        MobclickAgent.openActivityDurationTrack(false);

    }





    public static Context getContext() {
        return mContext;
    }

}
