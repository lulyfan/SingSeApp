package com.yxb.android.shengseshequ.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by caohui on 2016/7/20.
 */
public class UserBean extends BmobUser {
    private String nickName;
    private Integer userid;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
