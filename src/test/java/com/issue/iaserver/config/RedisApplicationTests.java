package com.issue.iaserver.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.issue.iaserver.entity.User;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest()
public class RedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testObj() throws Exception{
        User user = new User();
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("fdd2", user);
        boolean exists = redisTemplate.hasKey("fdd2");
        System.out.println("redis是否存在响应的key" + exists);
    }
}
