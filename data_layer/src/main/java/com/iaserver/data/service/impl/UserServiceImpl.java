package com.iaserver.data.service.impl;

import com.iaserver.data.mysql.dao.UserDao;
import com.iaserver.data.mysql.entity.UserDO;
import com.iaserver.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * user接口实现
 * User: 钟镇鸿
 * Date: 2020/4/3
 * Time: 18:29
 * Description:
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDO login(long id, String password) {
        //System.out.println(userDao == null);
        if(userDao.existsById(id)){
            Optional<UserDO> user = userDao.findById(id);
            UserDO userDO = user.get();
            if(userDO.getPassword().equals(password)) {
                return userDO;
            }
        }
        return null;
    }

    @Override
    public long addUser(UserDO userDO) {
        userDao.save(userDO);
        return userDO.getId();
    }
}
