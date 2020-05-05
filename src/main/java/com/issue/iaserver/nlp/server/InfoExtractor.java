package com.issue.iaserver.nlp.server;

import com.issue.iaserver.nlp.focus.Focus;
import com.issue.iaserver.nlp.keyword.Keyword;

import java.util.List;

public interface InfoExtractor {

    /**
     * 获得一个Issue所有的关键词
     * @param text Issue文本
     * @param textIdentify text标识
     * @return Issue的关键词
     */
    String[] findKeyWords(String textIdentify,String text);

    /**
     * 根据一个issue的关键词，获得Issue的所有关注点
     * @param keywords 关键词列表
     * @return 根据关键词匹配出的可能的关注点列表
     */
    List<Focus> findIssueFocus(List<Keyword> keywords);
}
