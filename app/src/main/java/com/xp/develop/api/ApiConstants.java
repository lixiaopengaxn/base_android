package com.xp.develop.api;

import com.xp.develop.R;
import com.xp.develop.base.BaseApplication;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/8/31
 * desc  :  utils about initialization
 */
public interface ApiConstants {

    interface WEB_VIEW {

//        String WEB_GITLAB_URL = "https://gitlab.com/lixiaopengaxn/base_android";

//        String WEB_GITLAB_URL = "http://www.pgyer.com/BaseNP";

        String WEB_GITLAB_URL = "https://www.baidu.com";

    }

    interface AUTO_SIZE {
        int DP = 400;
    }

    interface UM {
        String UM_KEY = BaseApplication.getContext().getString(R.string.um_key);
    }
}
