package com.issue.iaserver.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/17 22:49
 */
@Configuration
public class JedisConfig {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisConfig.class);

    public static final int EXPIRE_TIME = 1800;

    @Resource
    private RedisConfigProperty redisConfigProperty;

    /**
     * 连接池配置
     */
    @Bean(name = "jedisPoolConfig")
    @ConfigurationProperties(prefix = "spring.redis.pool-config")
    public JedisPoolConfig getRedisConfig() {
        return new JedisPoolConfig();
    }

    /**
     * jedis 连接池
     */
    @Bean(name = "jedisPool")
    public JedisPool jedisPool(@Qualifier(value = "jedisPoolConfig") final JedisPoolConfig jedisPoolConfig) {
        LOGGER.info("Jedis Pool build start ");
        String host = redisConfigProperty.getHost();
        Integer timeout = redisConfigProperty.getTimeout();
        int port = redisConfigProperty.getPort();
        String password = redisConfigProperty.getPassword();
        int database = redisConfigProperty.getDatabase();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
        LOGGER.info("Jedis Pool build success  host = {} , port = {} ", host, port);
        return jedisPool;
    }
}
