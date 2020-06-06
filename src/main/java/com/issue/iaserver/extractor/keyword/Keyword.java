package com.issue.iaserver.extractor.keyword;

//  关键词
public class Keyword implements Comparable<Keyword>{

    private long id;
    private String keyword;  // 关键词
    private String posTag;  // 关键词词性
    private int vote;  // 用户投票数

    public Keyword(String keyword, int vote){
        this.keyword = keyword;
        this.vote = vote;
    }

    public Keyword(long id,String keyword, int vote){
        this.id = id;
        this.keyword = keyword;
        this.vote = vote;
    }

    public Keyword(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPosTag() {
        return posTag;
    }

    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }

    @Override
    public int compareTo(Keyword o) {
        return Double.compare(this.vote,o.vote);
    }
}
