package com.issue.iaserver.data.queue;

import java.util.Date;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/15
 * Time: 18:28
 * Description:
 */
public class TaskAttribute {
    /** 第几个槽位 */
    private int soltIndex;
    /** 任务应该什么时候执行 */
    private Date executeTime;
    /** 任务加入槽位的时间 */
    private Date joinTime;

    public int getSoltIndex() {
        return soltIndex;
    }

    public void setSoltIndex(int soltIndex) {
        this.soltIndex = soltIndex;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

}
