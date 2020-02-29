package com.issue.iaserver.entity;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private Long id;
    private String loginName;
    private String password;
}
