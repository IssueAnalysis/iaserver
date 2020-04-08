package com.iaserver.data.mongdb;

/**
 * csv实体类
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 14:45
 * Description:
 */
public class CSVitem {

    /*- Summary
    - Issue key
    - Issue id
    - Parent id
    - Issue Type
    - Status
    - Project key
    - Project name
    - Project type
    - Project lead
    - Project description
    - Project url
    - Priority
    - Resolution
    - description
    - intension(更新)
    - consideration（更新）
    - comment（更新）*/
    private long CSVid;

    private String summary;
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
    private String description;
    private String comment;



    /**单项测试用构造器*/
    public CSVitem(String summary){
        this.summary = summary;
    }

    public CSVitem(long CSVid, String summary, String issue_key, String issue_id, String parent_id, String issue_type, String status, String project_key, String project_name, String project_type, String project_lead, String project_description, String project_url, String priority, String resolution, String description, String comment) {
        this.CSVid = CSVid;
        this.summary = summary;
        this.issue_key = issue_key;
        this.issue_id = issue_id;
        this.parent_id = parent_id;
        this.issue_type = issue_type;
        this.status = status;
        this.project_key = project_key;
        this.project_name = project_name;
        this.project_type = project_type;
        this.project_lead = project_lead;
        this.project_description = project_description;
        this.project_url = project_url;
        this.priority = priority;
        this.resolution = resolution;
        this.description = description;
        this.comment = comment;
    }

    public long getCSVid() {
        return CSVid;
    }

    public void setCSVid(long CSVid) {
        this.CSVid = CSVid;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CSVitem[" +
                "summary='" + summary + '\'' +
                ", issue_key='" + issue_key + '\'' +
                ", issue_id='" + issue_id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", issue_type='" + issue_type + '\'' +
                ", project_key='" + project_key + '\'' +
                ", project_name='" + project_name + '\'' +
                ", project_type='" + project_type + '\'' +
                ", project_lead='" + project_lead + '\'' +
                ", project_description='" + project_description + '\'' +
                ", project_url='" + project_url + '\'' +
                ", priority='" + priority + '\'' +
                ", resolution='" + resolution + '\'' +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                ']';
    }
}
