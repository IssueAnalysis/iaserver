package com.issue.iaserver.data.redis;

import java.awt.*;

/**
 * 这是获取的issue key
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 18:55
 * Description:
 */
public class IssueKey extends BasePrefix {

    public IssueKey(String prefix) {
        super(prefix);
    }

    public IssueKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
