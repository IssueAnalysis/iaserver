package com.issue.iaserver.data.mysql.entity;

import com.issue.iaserver.extractor.keyword.Keyword;

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
    private long csv_id;

    @Basic
    @Column(name = "issue_id")
    private long issue_id;

    @Basic
    @Column(name = "keyword_description")
    private String keyword_description;

    @Basic
    @Column(name = "posTag")
    private String posTag;  // 关键词词性

    @Basic
    @Column(name = "vote")
    private int vote;  // 用户投票数

    public KeywordDO() {}

    public KeywordDO(long id, long csv_id, long issue_id, String keyword_description, String posTag, int vote) {
        this.id = id;
        this.csv_id = csv_id;
        this.issue_id = issue_id;
        this.keyword_description = keyword_description;
        this.posTag = posTag;
        this.vote = vote;
    }

    public KeywordDO(Keyword keyword, long csv_id, long issue_id) {
        this.csv_id = csv_id;
        this.issue_id = issue_id;
        this.keyword_description = keyword.getKeyword();
        this.posTag = keyword.getPosTag();
        this.vote = keyword.getVote();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCsv_id() {
        return csv_id;
    }

    public void setCsv_id(Long csv_id) {
        this.csv_id = csv_id;
    }

    public Long getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(Long issue_id) {
        this.issue_id = issue_id;
    }

    public String getKeyword_description() {
        return keyword_description;
    }

    public void setKeyword_description(String keyword_description) {
        this.keyword_description = keyword_description;
    }

    public String getPosTag() {
        return posTag;
    }

    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
