package com.issue.iaserver.webserver.model;

public class Issue {

    private String summary;
    private String description;
    private String intention;
    private String consideration;

    public Issue(String summary, String description, String intention, String consideration) {
        this.summary = summary;
        this.description = description;
        this.intention = intention;
        this.consideration = consideration;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention;
    }

    public String getConsideration() {
        return consideration;
    }

    public void setConsideration(String consideration) {
        this.consideration = consideration;
    }
}
