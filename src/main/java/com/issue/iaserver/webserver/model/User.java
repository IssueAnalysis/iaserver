package com.issue.iaserver.webserver.model;

import com.issue.iaserver.data.mysql.entity.UserDO;

public class User {
    private long user_id;
    private String user_name;

    public User(){}

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public User(UserDO userDO){
        this.user_id = userDO.getId();
        this.user_name = userDO.getName();
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }
}
