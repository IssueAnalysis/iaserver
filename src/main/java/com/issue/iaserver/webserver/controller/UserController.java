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

    @RequestMapping("/log_in")
    public boolean logIn(@RequestParam(name="username")String username,
                        @RequestParam(name="password")String password,
                        HttpSession session) throws BusinessException {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(ErrorType.PARAMETER_VALIDATION_ERROR, "用户名密码不能为空");
        }
        UserDO user = userService.login(username, password);
        if(user!= null){
            session.setAttribute("user_id", user.getId());
            return true;
        }else{
            throw new BusinessException(ErrorType.PARAMETER_VALIDATION_ERROR,"用户名密码错误");
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
        User user = new User(userService.getOne(user_id));
        return user;
    }
}
