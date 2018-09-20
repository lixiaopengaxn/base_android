package com.xp.develop.mvp.entity;

import com.xp.develop.base.BaseResponse;

/**
 * author :  xpxn
 * blog  :  https://blog.csdn.net/qq_38729449
 * time  :  2018/9/17
 * desc  :  utils about initialization
 */
public class LoginModel extends BaseResponse<LoginModel> {

    private String userId;
    private String userName ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
