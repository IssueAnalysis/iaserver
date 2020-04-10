package data.service.impl;

import data.mysql.dao.UserDao;
import data.mysql.entity.UserDO;
import data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public UserDO login(String name, String password) {
        //System.out.println(userDao == null);
        ArrayList<UserDO> userDOArrayList = userDao.getUserByName(name);
        if(userDOArrayList.size() == 1){
            UserDO userDO = userDOArrayList.get(0);
            if(userDO.getPassword().equals(password)) {
                return userDO;
            }
        }
        return null;
    }

    @Override
    public long addUser(UserDO userDO) {
        String name = userDO.getName();
        ArrayList<UserDO> userDOS = userDao.getUserByName(name);
        if(userDOS.size() == 0){
            userDao.save(userDO);
            return userDO.getId();
        }
        return -1;
    }

    @Override
    public UserDO getOne(long id){
        return userDao.getOne(id);
    }
}
