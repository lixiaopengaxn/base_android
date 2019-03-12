package com.xn.xp.customtoast;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

public class BottomToast extends Toast {

    private BottomToast(Context context) {
        super(context);
    }

    /**
     * show the toast in the app views.
     *
     * @param viewGroup
     * @param message
     * @param time
     * @return
     */
    public static Toast make(@NonNull ViewGroup viewGroup, String message, long time) {
        return make(Position.BOTTOM, viewGroup, message, time);
    }

    /**
     * show the toast in the android window
     *
     * @param context
     * @param message
     * @param time
     * @return
     */
    public static Toast make(@NonNull Context context, String message, long time) {
        return make(Position.BOTTOM, context, message, time);
    }
}
