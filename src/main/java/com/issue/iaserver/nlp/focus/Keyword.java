package com.issue.iaserver.nlp.focus;

//  关键词
public class Keyword implements Comparable<Keyword>{

    String keyword;  // 关键词
    int vote;  // 用户投票数


    @Override
    public int compareTo(Keyword o) {
        return Double.compare(this.vote,o.vote);
    }
}
