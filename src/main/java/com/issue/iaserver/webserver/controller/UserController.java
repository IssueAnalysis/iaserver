package com.issue.iaserver.webserver.controller;


import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.issue.iaserver.webserver.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/log_in")
    public boolean login(@RequestParam String username, @RequestParam String password, HttpSession session) {

        return true;
    }

    @RequestMapping("/log_out")
    public void logout(HttpSession session) {

    }

    @RequestMapping("/sign_up")
    public void signup(@RequestParam String username, @RequestParam String password){
        UserDO userDO = new UserDO();
        userDO.setName(username);
        userDO.setPassword(password);
        userService.addUser(userDO);
    }

    @RequestMapping("/info")
    public User getUserInfo(HttpSession session) {
        User user = new User();
        user.setUser_name("zzz");
        return user;
    }
}
