package com.issue.iaserver.webserver.model;

/**
 * @author songjinze
 * @date 2020/5/19 11:29 下午
 */
public class StatisticKeyword {
    private String description;

    public StatisticKeyword(com.issue.iaserver.extractor.keyword.Keyword keyword){
        this.description = keyword.getKeyword();
    }

    public StatisticKeyword(String description) {
        this.description = description;
    }

    public StatisticKeyword(){}


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
