package com.issue.iaserver.redis.client;

import redis.clients.jedis.Jedis;

import java.io.IOException;
/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/20 23:04
 */
public interface RedisClientInvoker<T> {

    /**
     * invoke
     * @param jedis
     * @return
     * @throws IOException
     */
    T invoke(Jedis jedis) throws IOException;
}
