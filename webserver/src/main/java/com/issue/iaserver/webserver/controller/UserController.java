package com.issue.iaserver.webserver.controller;


import com.issue.iaserver.webserver.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping("/log_in")
    public boolean login(@RequestParam String user_name, @RequestParam String passed, HttpSession session) {

        return true;
    }

    @RequestMapping("/log_out")
    public boolean logout(HttpSession session) {

        return true;
    }

    @RequestMapping("/info")
    public User getUserInfo(HttpSession session) {
        User user = new User();
        user.setUser_name("zzz");
        return user;
    }
}
