package com.issue.iaserver.data.mysql.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 这个类把四种对应关系放在一起
 * IssueID 关键词id 投票用户id（这个用来标识那些用户给哪个issue的哪个关键词投过票）
 * IssueID 关注点id 投票用户 id（这个用来标识那些用户给哪个issue的哪个关注点投过票）
 * IssueID 关键词id 票数（issue的某个关键词的票数）
 * IssueID 关注点id  票数（issue的某个关注点的票数）
 *
 * 有空值，根据对应关系设置
 *
 * User: 钟镇鸿
 * Date: 2020/5/20
 * Time: 19:37
 * Description:
 */

@Entity
@Table(name="vote")
public class VoteDO implements Serializable {

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
    @Column(name = "focus_id")
    private long focus_id;

    @Basic
    @Column(name = "keyword_id")
    private long keyword_id;

    @Basic
    @Column(name = "user_id")
    private long user_id;

    @Basic
    @Column(name = "vote")
    private int vote;

    public VoteDO(long id, long csv_id, long item_id, long focus_id, long keyword_id, long user_id, int vote) {
        this.id = id;
        this.csv_id = csv_id;
        this.item_id = item_id;
        this.focus_id = focus_id;
        this.keyword_id = keyword_id;
        this.user_id = user_id;
        this.vote = vote;
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

    public long getFocus_id() {
        return focus_id;
    }

    public void setFocus_id(long focus_id) {
        this.focus_id = focus_id;
    }

    public long getKeyword_id() {
        return keyword_id;
    }

    public void setKeyword_id(long keyword_id) {
        this.keyword_id = keyword_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
