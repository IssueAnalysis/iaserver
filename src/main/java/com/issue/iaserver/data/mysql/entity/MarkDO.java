package com.issue.iaserver.data.mysql.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 记录被扫描过的issue
 * User: 钟镇鸿
 * Date: 2020/5/16
 * Time: 18:57
 * Description:
 */
@Entity
@Table(name="mark")
public class MarkDO implements Serializable {

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
    @Column(name = "is_marked")
    //isMarked为true，则为标记过了，false为未被标记
    private boolean isMarked;

    public MarkDO() {}

    public MarkDO(long id, long csv_id, long item_id, boolean isMarked) {
        this.id = id;
        this.csv_id = csv_id;
        this.item_id = item_id;
        this.isMarked = isMarked;
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

    public boolean getIsMarked() {
        return isMarked;
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }
}
