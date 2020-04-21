package com.issue.iaserver.nlp.focus;

//  关键词
public class Keyword implements Comparable<Keyword>{

    String keyword;  // 关键词

    int vote;  // 用户投票数

    public Keyword(String keyword, int vote){
        this.keyword = keyword;
        this.vote = vote;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public int compareTo(Keyword o) {
        return Double.compare(this.vote,o.vote);
    }
}
