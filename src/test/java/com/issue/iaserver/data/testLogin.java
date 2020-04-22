package com.issue.iaserver.data;

import com.issue.iaserver.Main;
import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class testLogin {
    @Autowired
    UserService userService;
    @Test
    public void testLogin() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // TODO 原来参数格式有问题
        UserDO user = userService.login("Z", "123456");
        assertEquals(user.getName(), "Z");
    }

    @Test
    public void testAdd(){
       UserDO user = new UserDO(0, "Z", "123456", "管理员");
       userService.addUser(user);
    }
}
