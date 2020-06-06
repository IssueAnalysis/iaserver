package com.issue.iaserver.webserver.model;

/**
 * @author songjinze
 * @date 2020/5/14 5:23 下午
 */
public class Keyword {

    private long id;

    private String description;

    private String vote;

    private boolean isVoted;

    public void setVoted(boolean voted) {
        isVoted = voted;
    }

    public Keyword(com.issue.iaserver.extractor.keyword.Keyword keyword){
        this.id = keyword.getId();
        this.description = keyword.getKeyword();
        this.vote = String.valueOf(keyword.getVote());
        this.isVoted = false;
    }

    public long getId() {
        return id;
    }

    public boolean isVoted() {
        return isVoted;
    }

    public String getDescription() {
        return description;
    }

    public String getVote() {
        return vote;
    }

    public boolean getIsVoted() {
        return isVoted;
    }
}
