package com.issue.iaserver.data.mysql.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 收藏对应表
 * User: 钟镇鸿
 * Date: 2020/4/15
 * Time: 19:22
 * Description:
 */
@Entity
@Table(name="collect")
public class CollectDO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "csv_id")
    private long csv_id;

    @Basic
    @Column(name = "item_id")
    private long item_id;

    @Basic
    @Column(name = "user_id")
    private long user_id;

    public CollectDO(){}

    public CollectDO(long id, long csv_id, long item_id, long user_id) {
        this.id = id;
        this.csv_id = csv_id;
        this.item_id = item_id;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCsv_id() {
        return csv_id;
    }

    public void setCsv_id(long csv_id) {
        this.csv_id = csv_id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
