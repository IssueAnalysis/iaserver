package com.issue.iaserver.webserver.model;

public class IssueBrief {

    private long id;
    private long csvId;

    private String summary;
    private String briefDescription;
    private boolean collect;


    public IssueBrief(long id, long csvId, String summary, String briefDescription, boolean isCollected) {
        this.id = id;
        this.csvId = csvId;
        this.summary = summary;
        this.briefDescription = briefDescription;
        collect = isCollected;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCsvId() {
        return csvId;
    }

    public void setCsvId(long csvId) {
        this.csvId = csvId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }
}

