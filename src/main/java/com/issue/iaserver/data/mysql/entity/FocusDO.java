package com.issue.iaserver.data.mysql.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.issue.iaserver.nlp.focus.Focus;
import com.issue.iaserver.nlp.focus.Keyword;

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

    /*public static void main(String [] args){
        List<Keyword> keywordDOS = new ArrayList<>();
        Keyword q1 = new Keyword("ok", 1);
        Keyword q2 = new Keyword("12", 3);
        Keyword q3 = new Keyword("lp", 2);
        keywordDOS.add(q1);keywordDOS.add(q2);keywordDOS.add(q3);
        String jsonOutput= JSON.toJSONString(keywordDOS);
        System.out.println(jsonOutput);

        List<Keyword> keywordDOS1 = JSON.parseObject(jsonOutput, new TypeReference<List<Keyword>>(){});
        keywordDOS1.sort(new KeywordComparator());
        System.out.println(keywordDOS1.get(0).getVote());
    }*/

    //能获得vote正序的关键词列表
    public List<Keyword> getKeywordList(){
        List<Keyword> keywordDOS1 = JSON.parseObject(this.keywordJson, new TypeReference<List<Keyword>>(){});
        keywordDOS1.sort(new KeywordComparator());
        return keywordDOS1;
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
