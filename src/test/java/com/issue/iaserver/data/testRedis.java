package com.issue.iaserver.data;

import com.issue.iaserver.Main;
import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.redis.RedisPoolFactory;
import com.issue.iaserver.data.redis.UserKey;
import com.issue.iaserver.data.service.RedisService;
import com.issue.iaserver.data.service.UserService;
import com.issue.iaserver.data.service.impl.RedisServiceImpl;
import com.issue.iaserver.webserver.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 18:43
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class testRedis {
    @Autowired
    RedisPoolFactory redisPoolFactory;
    @Autowired
    RedisServiceImpl redisService;
    @Autowired
    UserService userService;

    @Test
    public void test1(){
        JedisPool jedisPool = redisPoolFactory.JedisPoolFactory();
        assertEquals("",jedisPool.toString());
    }

    @Test
    public void test2(){

        User user = userService.getOne(1);
        redisService.set(UserKey.getById, user.getUser_id()+"", user);
        //redisService.get(UserKey.getById, ""+1, (User)user);
    }
}
