package com.issue.iaserver.nlp.focus;

import java.util.List;

// 关注点
public class Focus implements Comparable<Focus> {

    String focusDescription;    // 关注点描述
    List<Keyword> keywordList;  // 关键词列表
    String focusType;           // 关注点类型


    // 非持久层部分
    double p;                   // 权值


    @Override
    public int compareTo(Focus o) {
        return Double.compare(this.p, o.p);
    }
}
