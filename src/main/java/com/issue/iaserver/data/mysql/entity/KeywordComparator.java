package com.issue.iaserver.data.mysql.entity;

import com.issue.iaserver.nlp.keyword.Keyword;

import java.util.Comparator;

/**
 * 字符串排序
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 19:25
 * Description:
 */
public class KeywordComparator implements Comparator<Keyword> {

    @Override
    public int compare(Keyword o1, Keyword o2) {
        int key1 = o1.getVote();
        int key2 = o2.getVote();
        return key2 - key1;
    }
}
