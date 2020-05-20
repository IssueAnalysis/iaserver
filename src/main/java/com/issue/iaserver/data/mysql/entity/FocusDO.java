package com.issue.iaserver.data.mysql.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.issue.iaserver.extractor.keyword.Keyword;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 关注点实体类
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 18:33
 */
@Entity
@Table(name="focus")
public class FocusDO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Basic
    @Column(name = "focus_description")
    private String focusDescription;    // 关注点描述

    @Basic
    @Column(name = "keywordJson")
    private String keywordJson;  // 关键词列表

    @Basic
    @Column(name = "focus_type")
    private String focusType;           // 关注点类型

    public FocusDO(){}
    public FocusDO(long id, String focusDescription, List<Keyword> keywordList, String focusType) {
        this.id = id;
        this.focusDescription = focusDescription;
        this.keywordJson = JSON.toJSONString(keywordList); //用json存
        this.focusType = focusType;
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
}
