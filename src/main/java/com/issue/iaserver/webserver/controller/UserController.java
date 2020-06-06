package com.issue.iaserver.webserver.controller;


import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.service.UserService;
import com.issue.iaserver.webserver.common.BusinessException;
import com.issue.iaserver.webserver.common.ErrorType;
import com.mysql.cj.AbstractQuery;
import org.springframework.beans.factory.annotation.Autowired;
import com.issue.iaserver.webserver.model.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    //对明文的密码进行两次md5，md5(md5(明文+固定salt)+固定salt)
    @RequestMapping("/log_in")
    public boolean logIn(@RequestParam(name="username")String username,
                        @RequestParam(name="password")String password,
                        HttpSession session) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }
        UserDO user = userService.login(username, password);
        if(user!= null){
            session.setAttribute("user_id", user.getId());
            return true;
        }else{
            return false;
        }

    }

    @RequestMapping("/log_out")
    public void logOut(HttpSession session) {
        session.removeAttribute("user_id");
    }

    @RequestMapping("/sign_up")
    public void signup(@RequestParam String username, @RequestParam String password, HttpSession session){
        UserDO userDO = new UserDO();
        userDO.setName(username);
        userDO.setPassword(password);
        long user_id = userService.addUser(userDO);
        session.setAttribute("user_id", user_id);
    }

    @RequestMapping("/info")
    public User getUserInfo(HttpSession session) {
        long user_id = (long) session.getAttribute("user_id");
        User user = userService.getOne(user_id);
        return user;
    }
}
