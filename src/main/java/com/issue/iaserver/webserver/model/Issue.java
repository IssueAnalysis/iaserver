package com.issue.iaserver.webserver.model;

import com.issue.iaserver.data.mongodb_es.CSVitem;
import com.issue.iaserver.format.model.RichDescription;

public class Issue {

    private String issue_key;
    private String issue_id;
    private String parent_id;
    private String issue_type;
    private String status;
    private String project_key;
    private String project_name;
    private String project_type;
    private String project_lead;
    private String project_description;
    private String project_url;
    private String priority;
    private String resolution;
    private String summary;
    private String description;
    private String intention;
    private String consideration;
    private String briefDescription;
    private RichDescription richDescription;

    public Issue(CSVitem csVitem){
        this.issue_key = csVitem.getIssue_key();
        this.issue_id = csVitem.getIssue_id();
        this.parent_id = csVitem.getParent_id();
        this.issue_type = csVitem.getIssue_type();
        this.status = csVitem.getStatus();
        this.project_key = csVitem.getProject_key();
        this.project_name = csVitem.getProject_name();
        this.project_type = csVitem.getProject_type();
        this.project_lead = csVitem.getProject_lead();
        this.project_description = csVitem.getProject_description();
        this.project_url = csVitem.getProject_url();
        this.priority = csVitem.getPriority();
        this.resolution = csVitem.getResolution();

        this.summary = csVitem.getSummary();
        this.description = csVitem.getDescription();
        this.intention = csVitem.getIntension();
        this.consideration = csVitem.getConsideration();
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


    public String getIssue_key() {
        return issue_key;
    }

    public void setIssue_key(String issue_key) {
        this.issue_key = issue_key;
    }

    public String getIssue_id() {
        return issue_id;
    }

    public void setIssue_id(String issue_id) {
        this.issue_id = issue_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getIssue_type() {
        return issue_type;
    }

    public void setIssue_type(String issue_type) {
        this.issue_type = issue_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProject_key() {
        return project_key;
    }

    public void setProject_key(String project_key) {
        this.project_key = project_key;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_lead() {
        return project_lead;
    }

    public void setProject_lead(String project_lead) {
        this.project_lead = project_lead;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getProject_url() {
        return project_url;
    }

    public void setProject_url(String project_url) {
        this.project_url = project_url;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }


    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public RichDescription getRichDescription() {
        return richDescription;
    }

    public void setRichDescription(RichDescription richDescription) {
        this.richDescription = richDescription;
    }
}
