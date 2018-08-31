package com.xp.develop.utils;


/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/31
 * desc  :  utils about initialization
 */
public class Utils {

    private static Utils instance = null;

    public Utils() {

    }

    //同步代码快的demo加锁，安全高效
    public static Utils getInstance() {
        if (instance == null)
            synchronized (Utils.class) {
                if (instance == null)
                    instance = new Utils();
            }

        return instance;

    }

    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
