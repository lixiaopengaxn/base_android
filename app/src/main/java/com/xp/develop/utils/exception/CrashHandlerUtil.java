package com.xp.develop.utils.exception;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * .--,       .--,
 * ( (  \.---./  ) )
 * '.__/o   o\__.'
 * {=  ^  =}
 * >  -  <
 * /       \
 * //       \\
 * //|   .   |\\
 * "'\       /'"_.-~^`'-.
 * \  _  /--'         `
 * ___)( )(___
 * (((__) (__)))    高山仰止,景行行止.虽不能至,心向往之。
 *
 * @author lipeng
 * @title: sdv
 * @projectName base_android
 * @description: TODO
 * @date 2019-05-3015:35
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class CrashHandlerUtil implements Thread.UncaughtExceptionHandler {

    private static CrashHandlerUtil myCrashHandler;

    private Context mContext;

    private CrashHandlerUtil(Context context) {
        mContext = context;
    }

    public static synchronized CrashHandlerUtil getInstance(Context context) {
        if (null == myCrashHandler) {
            myCrashHandler = new CrashHandlerUtil(context);
        }
        return myCrashHandler;
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        long threadId = thread.getId();
        String message = throwable.getMessage();
        String localizedMessage = throwable.getLocalizedMessage();
        Log.i("CrashHandlerUtil", "------------------------------------------------------");
        Log.i("CrashHandlerUtil", "threadId = " + threadId);
        Log.i("CrashHandlerUtil", "message = " + message);
        Log.i("CrashHandlerUtil", "localizedMessage = " + localizedMessage);
        Log.i("CrashHandlerUtil", "------------------------------------------------------");
        throwable.printStackTrace();
        Log.i("CrashHandlerUtil", "------------------------------------------------------");

        // TODO 下面捕获到异常以后要做的事情，可以重启应用，获取手机信息上传到服务器等
        Log.i("CrashHandlerUtil", "------------------应用被重启----------------");
        // 重启应用
        mContext.startActivity(mContext.getPackageManager().getLaunchIntentForPackage(mContext.getPackageName()));
        //干掉当前的程序
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
