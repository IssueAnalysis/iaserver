package com.issue.iaserver.data.mysql.entity;

import javax.persistence.*;
import java.util.*;

/**
 * focusDO和issueDO的对应关系，每个issue的同一关注点票数不一样，另增票数字段
 * User: 钟镇鸿
 * Date: 2020/4/21
 * Time: 20:25
 * Description:
 */
@Entity
@Table(name="issue2focus")
public class Issue2FocusDO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic
    @Column(name = "csv_id")
    private String csv_id;

    @Basic
    @Column(name = "issue_id")
    private String issue_id;

    @Basic
    @Column(name = "focus_list")
    private Long focus_list;

    @Basic
    @Column(name = "vote")
    private String vote;

    public Issue2FocusDO(long id, String csv_id, String issue_id, Long focus_list, String vote) {
        this.id = id;
        this.csv_id = csv_id;
        this.issue_id = issue_id;
        this.focus_list = focus_list;
        this.vote = vote;
    }

    public Issue2FocusDO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCsv_id() {
        return csv_id;
    }

    public void setCsv_id(String csv_id) {
        this.csv_id = csv_id;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public Long getFocus_list() {
        return focus_list;
    }

    public void setFocus_list(Long focus_list) {
        this.focus_list = focus_list;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }
}
