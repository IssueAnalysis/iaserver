package com.iaserver.data.service;

import com.iaserver.data.mysql.entity.UserDO;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/3
 * Time: 17:42
 * Description:
 */
public interface UserService {
    //登录判断
    UserDO login(long id, String password);
    //注册
    long addUser(UserDO userDO);

    UserDO getOne(long id);
}
