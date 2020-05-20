package com.issue.iaserver.data.mysql.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 关键词实体类
 * User: 钟镇鸿
 * Date: 2020/5/20
 * Time: 19:27
 * Description:
 */
@Entity
@Table(name="keyword")
public class KeywordDO implements Serializable {

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
    @Column(name = "keyword_description")
    private String keyword_description;

    public KeywordDO(long id, String csv_id, String issue_id, String keyword_description) {
        this.id = id;
        this.csv_id = csv_id;
        this.issue_id = issue_id;
        this.keyword_description = keyword_description;
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

    public String getKeyword_description() {
        return keyword_description;
    }

    public void setKeyword_description(String keyword_description) {
        this.keyword_description = keyword_description;
    }
}
