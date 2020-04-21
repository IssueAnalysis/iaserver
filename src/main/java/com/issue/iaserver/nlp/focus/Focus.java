package com.issue.iaserver.nlp.focus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.issue.iaserver.data.mysql.entity.FocusDO;

import java.util.List;

// 关注点
public class Focus implements Comparable<Focus> {

    String focusDescription;    // 关注点描述
    List<Keyword> keywordList;  // 关键词列表
    String focusType;           // 关注点类型


    // 非持久层部分
    double p;                   // 权值

    Focus(FocusDO focusDO){
        this.focusDescription = focusDO.getFocusDescription();
        this.focusType = focusDO.getFocusType();
        this.keywordList = focusDO.getKeywordList();
    }

    @Override
    public int compareTo(Focus o) {
        return Double.compare(this.p, o.p);
    }
}
