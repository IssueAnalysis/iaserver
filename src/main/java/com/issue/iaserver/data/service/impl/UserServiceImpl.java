package com.issue.iaserver.data.service.impl;

import com.issue.iaserver.data.mysql.dao.UserDao;
import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.redis.UserKey;
import com.issue.iaserver.data.service.RedisService;
import com.issue.iaserver.data.service.UserService;
import com.issue.iaserver.data.util.MD5Util;
import com.issue.iaserver.data.util.UUIDUtil;
import com.issue.iaserver.webserver.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * user接口实现
 * User: 钟镇鸿
 * Date: 2020/4/3
 * Time: 18:29
 * Description:
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private RedisService redisService;

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
            String password = MD5Util.inputPassToDbPass(userDO.getPassword(), userDO.getSalt());
            userDO.setPassword(password);
            userDao.save(userDO);
            return userDO.getId();
        }
        return -1;
    }

    @Override
    public User getOne(long id){

        return new User(userDao.getOne(id));
    }


    public UserDO getById(long id) {
        //取缓存
        UserDO user = redisService.get(UserKey.getById, ""+id, UserDO.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = userDao.getOne(id);
        if(user != null) {
            redisService.set(UserKey.getById, ""+id, user);
        }
        return user;
    }

    public boolean updatePassword(String token, long id, String formPass) {
        //取user
        UserDO user = getById(id);
        if(user == null) {
            return false;
        }
        //更新数据库
        UserDO toBeUpdate = new UserDO();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        userDao.saveAndFlush(toBeUpdate);
        //处理缓存
        redisService.delete(UserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(UserKey.token, token, user);
        return true;
    }


    public UserDO getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        UserDO user = redisService.get(UserKey.token, token, UserDO.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }


    @Override
    public String login(String name, String formpassword, String exit) {
        ArrayList<UserDO> userDOArrayList = userDao.getUserByName(name);
        if(userDOArrayList.size() == 1){
            UserDO userDO = userDOArrayList.get(0);
            String dbPass = userDO.getPassword();
            String saltDB = userDO.getSalt();
            String ok = MD5Util.inputPassToFormPass(dbPass);
            System.out.println(ok);
            String calcPass = MD5Util.formPassToDBPass(formpassword, saltDB);
            if(calcPass.equals(dbPass)) {
                String token = UUIDUtil.uuid();
                return token;
            }
        }
        return null;
    }

    private void addCookie(HttpServletResponse response, String token, UserDO user) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
