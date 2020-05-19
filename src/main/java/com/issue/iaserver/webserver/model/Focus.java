package com.issue.iaserver.webserver.model;

/**
 * @author songjinze
 * @date 2020/5/14 5:23 下午
 */
public class Focus {
    private long id;
    private String description;
    private String type;
    private String vote;
    private boolean isVoted;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getVote() {
        return vote;
    }

    public boolean isVoted() {
        return isVoted;
    }
}