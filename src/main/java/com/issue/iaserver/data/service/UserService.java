package com.issue.iaserver.data.service;

import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.webserver.model.User;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/3
 * Time: 17:42
 * Description:
 */
public interface UserService {
    //登录判断
    UserDO login(String name, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    //注册
    long addUser(UserDO userDO);

    User getOne(long id);

    //String login(String name, String formpassword, String exit);
}
