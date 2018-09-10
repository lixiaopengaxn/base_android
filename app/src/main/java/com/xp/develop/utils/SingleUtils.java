package com.xp.develop.utils;


import android.support.annotation.ColorRes;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/31
 * desc  :  utils about initialization
 */
public class SingleUtils {

    private static SingleUtils instance = null;

    public SingleUtils() {

    }

    //同步代码快的demo加锁，安全高效
    public static SingleUtils getInstance() {
        if (instance == null)
            synchronized (SingleUtils.class) {
                if (instance == null)
                    instance = new SingleUtils();
            }

        return instance;

    }

    private @ColorRes int color;

    public @ColorRes int getColor() {
        return color;
    }

    public void setColor(@ColorRes int color) {
        this.color = color;
    }


}
