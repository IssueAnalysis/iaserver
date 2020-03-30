package com.issue.iaserver.redis.model;

/**
 * zet的分数
 *
 * @author 钟镇鸿
 * @since 2020/3/20 23:05
 */
public class RedisRangScoresModel<T> {
    private Double score;
    private T obj;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }


    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

}
