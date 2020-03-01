package com.issue.iaserver.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class User implements Serializable {
    @Id
    private Long id;
    private String loginName;
    private String password;
    public User(){}
    public User(long id, String loginName, String password){
        this.id = id;
        this.loginName = loginName;
        this.password = password;
    }
}
