package com.xn.xp.customtoast;

public interface ToastListener {
    void beforeToastShow(Toast toast);

    void afterToastShow(Toast toast);

    void beforeToastDismiss(Toast toast);

    void afterToastDismiss(Toast toast);
}
