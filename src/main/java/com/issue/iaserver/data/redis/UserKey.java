package com.issue.iaserver.data.redis;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * 用户的key
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 18:56
 * Description:
 */
public class UserKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 3600*24 * 2;

    public UserKey(String prefix) {
        super(prefix);
    }

    public UserKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey token = new UserKey(TOKEN_EXPIRE, "tk");
}
