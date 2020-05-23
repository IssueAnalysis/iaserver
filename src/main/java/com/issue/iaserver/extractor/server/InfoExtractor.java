package com.issue.iaserver.extractor.server;

import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;

import java.util.List;

public interface InfoExtractor {

    /**
     * 获得一个issue的所有关键词，并记录当前issue已被扫描
     * @param issueId issue的Id
     * @param csvId csvId 和issueId一起作为一个issue的唯一标识
     * @param text issue文本
     * @return 关键词列表
     */
    List<Keyword> findKeyWords(long issueId,long csvId,String text);

    /**
     * 获得一个issue的所有关注点，前提是issue之前已被扫描
     * @param issueId issue的Id
     * @param csvId csvId 和 issueId一起作为一个issue 的唯一标识
     * @return 关注点列表
     */
    List<Focus> findIssueFocus(long issueId, long csvId,String text);

    /**
     * 根据一个issue的关键词，获得Issue的所有关注点
     * @param keywords 关键词列表
     * @return 根据关键词匹配出的可能的关注点列表
     */
    List<Focus> findIssueFocus(List<Keyword> keywords);
}
