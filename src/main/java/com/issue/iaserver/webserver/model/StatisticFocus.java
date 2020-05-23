package com.issue.iaserver.webserver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/18 10:43 下午
 */
public class StatisticFocus {
    private long id;
    private String description;
    private String type;
    private List<StatisticKeyword> keywordList;

    public StatisticFocus(com.issue.iaserver.extractor.focus.Focus focus){
        this.id = focus.getId();
        this.description = focus.getFocusDescription();
        this.type = focus.getFocusType();
        List<com.issue.iaserver.extractor.keyword.Keyword> keywords = focus.getKeywordList();
        this.keywordList = new ArrayList<>(keywords.size());
        for(com.issue.iaserver.extractor.keyword.Keyword keyword : keywords){
            keywordList.add(new StatisticKeyword(keyword));
        }
    }

    public StatisticFocus(long id, String description, String type, List<StatisticKeyword> keywordList) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.keywordList = keywordList;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public List<StatisticKeyword> getKeywordList() {
        return keywordList;
    }
}
