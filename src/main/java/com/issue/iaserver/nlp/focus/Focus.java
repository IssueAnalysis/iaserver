package com.issue.iaserver.nlp.focus;

import com.issue.iaserver.data.mysql.entity.FocusDO;

import java.util.List;

// 关注点
public class Focus implements Comparable<Focus> {

    private String focusDescription;    // 关注点描述
    private List<Keyword> keywordList;  // 关键词列表
    private String focusType;           // 关注点类型

    // 非持久层部分
    double p;                   // 权值
    int count;


    Focus(FocusDO focusDO){
        this.focusDescription = focusDO.getFocusDescription();
        this.focusType = focusDO.getFocusType();
        this.keywordList = focusDO.getKeywordList();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFocusDescription() {
        return focusDescription;
    }

    public void setFocusDescription(String focusDescription) {
        this.focusDescription = focusDescription;
    }

    public List<Keyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<Keyword> keywordList) {
        this.keywordList = keywordList;
    }

    public String getFocusType() {
        return focusType;
    }

    public void setFocusType(String focusType) {
        this.focusType = focusType;
    }

    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }

    @Override
    public int compareTo(Focus o) {
        return Integer.compare(this.count, o.count);
    }
}
