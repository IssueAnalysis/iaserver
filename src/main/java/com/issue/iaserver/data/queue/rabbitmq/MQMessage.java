package com.issue.iaserver.data.queue.rabbitmq;

import com.issue.iaserver.data.mysql.entity.UserDO;

/**
 * 封装的消息
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 21:16
 * Description:
 */
public class MQMessage {
    private UserDO user;
    private long issueId;
    private long csvId;
    public UserDO getUser() {
        return user;
    }
    public void setUser(UserDO user) {
        this.user = user;
    }
    public long getIssueId() {
        return issueId;
    }
    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }
    public long getCsvId() {
        return csvId;
    }

    public void setCsvId(long csvId) {
        this.csvId = csvId;
    }
}
