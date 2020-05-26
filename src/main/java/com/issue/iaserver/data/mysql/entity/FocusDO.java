package com.issue.iaserver.data.mysql.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * TODO: 测试keywordJson
 * 关注点实体类
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 18:33
 */
@Entity
@Table(name="focus")
@Proxy(lazy = false)
public class FocusDO implements Serializable {

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
    @Column(name = "focus_description")
    private String focusDescription;    // 关注点描述

    @Basic
    @Column(name = "keywordJson")
    private String keywordJson;  // 关键词列表

    @Basic
    @Column(name = "focus_type")
    private String focusType;           // 关注点类型

    @Basic
    @Column(name = "vote")
    private int vote;  // 用户投票数

    public FocusDO(){}

    public FocusDO(long id, long csv_id, long issue_id, String focusDescription,
                   List<Keyword> keywordList, String focusType, int vote) {
        this.id = id;
        this.csv_id = csv_id;
        this.issue_id = issue_id;
        this.focusDescription = focusDescription;
        this.keywordJson =  JSON.toJSONString(keywordList);//用json存
        this.focusType = focusType;
        this.vote = vote;
    }

    public FocusDO(Focus focus, long csvId, long issueId) {
        this.id = focus.getId();
        this.csv_id = csvId;
        this.issue_id = issueId;
        this.focusDescription = focus.getFocusDescription();
        this.keywordJson = JSON.toJSONString(focus.getKeywordList());
        this.focusType = focus.getFocusType();
        this.vote = (int) focus.getVote();
    }

    public FocusDO(Focus focus) {
        this.id = focus.getId();
        this.focusDescription = focus.getFocusDescription();
        this.keywordJson = JSON.toJSONString(focus.getKeywordList());
        this.focusType = focus.getFocusType();
        this.vote = (int) focus.getVote();
    }

    public void setKeywordList(List<Keyword> keywordList){
        this.keywordJson = JSON.toJSONString(keywordList);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFocusDescription() {
        return focusDescription;
    }



    public void setFocusDescription(String focusDescription) {
        this.focusDescription = focusDescription;
    }

    public List<Keyword> getKeywordList(){
        return JSONObject.parseArray(this.keywordJson,Keyword.class);
    }

    public String getKeywordJson() {

        return keywordJson;

    }



    public void setKeywordJson(String keywordJson) {

        this.keywordJson = keywordJson;

    }



    public String getFocusType() {

        return focusType;

    }



    public void setFocusType(String focusType) {

        this.focusType = focusType;

    }

    public long getCsv_id() {
        return csv_id;
    }

    public void setCsv_id(long csv_id) {
        this.csv_id = csv_id;
    }

    public long getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(long issue_id) {
        this.issue_id = issue_id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

}