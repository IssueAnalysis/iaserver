package com.issue.iaserver.data.service;

import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.queue.rabbitmq.MQMessage;
import com.issue.iaserver.data.redis.KeyPrefix;
import com.issue.iaserver.data.redis.UserKey;

/**
 * 通过这个service来提供redis 的服务
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 18:37
 */
public interface RedisService {

    <T> T get(KeyPrefix prefix, String key, Class<T> clazz);
    <T> boolean set(KeyPrefix prefix, String key,  T value);
    boolean delete(KeyPrefix prefix, String key);
}
