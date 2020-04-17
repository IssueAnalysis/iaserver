package com.issue.iaserver.data.redis;

/**
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 18:50
 * Description:通用缓存key的封装
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
