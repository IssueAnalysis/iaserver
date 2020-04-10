package data.service;

import data.mysql.entity.UserDO;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/3
 * Time: 17:42
 * Description:
 */
public interface UserService {
    //登录判断
    UserDO login(String name, String password);
    //注册
    long addUser(UserDO userDO);

    UserDO getOne(long id);
}
