package com.issue.iaserver.data.redis;

/**
 * base缓存
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 18:53
 * Description:
 */
public abstract class BasePrefix implements KeyPrefix {

    /**过期时间*/
    private int expireSeconds;

    private String prefix;

    //默认0代表永不过期
    public BasePrefix(String prefix) {//0代表永不过期
        this(0, prefix);
    }

    public BasePrefix( int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    public int expireSeconds() {
        return expireSeconds;
    }

    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":" + prefix;
    }
}
