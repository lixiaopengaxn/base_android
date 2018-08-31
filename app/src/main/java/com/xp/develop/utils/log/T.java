package com.xp.develop.utils.log;

import android.content.Context;
import android.widget.Toast;


public class T
{

    private static Context sContext;
    private static Toast sToast;

    public static void init(Context context)
    {
        sContext = context.getApplicationContext();
        sToast = Toast.makeText(sContext, "", Toast.LENGTH_SHORT);
    }


    public static void s(String msg)
    {
        sToast.setText(msg);
        sToast.setDuration(Toast.LENGTH_SHORT);
        sToast.show();
    }


    public static void l(String msg)
    {
        sToast.setText(msg);
        sToast.setDuration(Toast.LENGTH_LONG);
        sToast.show();
    }
}
