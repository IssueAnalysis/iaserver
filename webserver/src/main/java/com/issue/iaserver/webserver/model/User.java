package com.issue.iaserver.webserver.model;

public class User {
    String user_id;
    private String user_name;

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }
}
