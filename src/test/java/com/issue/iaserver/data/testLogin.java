package com.issue.iaserver.data;

import com.issue.iaserver.data.IaserverApplication;
import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IaserverApplication.class)
public class testLogin {
    @Autowired
    UserService userService;
    @Test
    public void testLogin(){
        // TODO 原来参数格式有问题
//        UserDO user = userService.login((long)2, "123456");
//        assertEquals(user.getName(), "B");
    }

    @Test
    public void testAdd(){
        //long id, String name, String password, String job
        UserDO user = new UserDO(0, "E", "123456", "管理员");
        userService.addUser(user);
    }
}
