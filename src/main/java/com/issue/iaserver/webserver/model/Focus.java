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

    public Focus(com.issue.iaserver.extractor.focus.Focus focus){
        this.id = focus.getId();
        this.description = focus.getFocusDescription();
        this.type = focus.getFocusType();
        this.vote = String.valueOf(focus.getVote());
        this.isVoted = false;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public void setVoted(boolean voted) {
        isVoted = voted;
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

    public String getVote() {
        return vote;
    }

    public boolean isVoted() {
        return isVoted;
    }
}
