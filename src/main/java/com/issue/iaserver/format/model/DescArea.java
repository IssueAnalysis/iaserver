package com.issue.iaserver.format.model;

public class DescArea {
    private String type;        //"code", "text", "exception"
    private String content;

    public DescArea(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
