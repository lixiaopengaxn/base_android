package com.xp.develop.api;

import android.app.Application;



/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/31
 * desc  :  utils about initialization
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        Utils.getInstance().setColor(ContextCompat.getColor(this,R.color.red));
    }
}
