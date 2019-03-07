package com.xp.develop.utils;

import android.util.Log;

/**
 * @author : xp
 * @blog :  https://blog.csdn.net/qq_38729449
 * @create :  2019/3/7 15:38
 * @Describe :  utils about initialization
 */
public class UtilsLog {
    public static boolean isDebug = true;

    private final static String APP_TAG = "哈哈哈哈哈哈哈哈～嗝～";

    /**
     * 获取相关数据:类名,方法名,行号等.用来定位行<br>
     * at cn.utils.MainActivity.onCreate(MainActivity.java:17) 就是用來定位行的代碼<br>
     *
     * @return [ Thread:main, at
     * cn.utils.MainActivity.onCreate(MainActivity.java:17)]
     */
    private static String getFunctionName(String tag) {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts != null) {
            for (StackTraceElement st : sts) {
                if (st.isNativeMethod()) {
                    continue;
                }
                if (st.getClassName().equals(Thread.class.getName())) {
                    continue;
                }
                if (st.getClassName().equals(UtilsLog.class.getName())) {
                    continue;
                }
                return "["+tag+"的打印代码位置"+st.getClassName() + "." + st.getMethodName()
                        + "(" + st.getFileName() + ":" + st.getLineNumber() + ")" + " ]\n"+"   ";
            }
        }
        return null;
    }

    /**
     * 不带行数提示
     * @param msg
     */
    public static void e(String msg) {
        if (isDebug) {
            Log.e(APP_TAG,msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, getMsgFormat(msg,tag));
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(APP_TAG,msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, getMsgFormat(msg,tag));
        }
    }

    /**
     * 输出格式定义
     */
    private static String getMsgFormat(String msg,String tag) {
        return msg + "\n" + getFunctionName(tag);
    }

}
