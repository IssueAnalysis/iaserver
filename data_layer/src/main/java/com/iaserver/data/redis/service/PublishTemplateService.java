package com.iaserver.data.redis.service;

/**
 * Redis 发布订阅 -相关操作
 *
 * @author 钟镇鸿
 * @since 2020/3/16 22:57
 */
public interface PublishTemplateService {

    Long publish(String key, Object value);
}
