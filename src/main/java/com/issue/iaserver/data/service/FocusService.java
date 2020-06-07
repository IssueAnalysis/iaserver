package com.issue.iaserver.data.service;

import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.mysql.entity.KeywordDO;
import com.issue.iaserver.data.mysql.entity.VoteDO;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;

import java.util.*;

/**
 * 操作关注点的接口
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 19:41
 * Description:
 */
public interface FocusService {

    /**获取全部的Focus*/
    List<FocusDO> getAllFocus();

    /**新增focus*/
    long addFocus(Focus focus);

    boolean updateFocus(Focus focus);

    /**
     * 当前issue是否已经被信息提取过
     * @param issueId issue id
     * @param csvId csv id，和issueId到一起作为issue的唯一标识
     * @return 是否被提取过
     */
    boolean isIssueExtracted(long issueId, long csvId);

    /**
     * 将当前一个issue标记为已被提取过
     * @param issueId issue id
     * @param csvId csv id， 和issueId一起作为issue的唯一标识
     * @return 是否标记成功
     */
    boolean markIssueExtracted(long issueId, long csvId);

    /**
     * 根据issue的Id 和 csv Id 获得已经标记过的issue的关键词
     * @param issueId issue id
     * @param csvId csv id
     * @return issue的关键词
     */
    List<Keyword> getMarkedIssueKeywords(long issueId, long csvId);

    /**
     * 获得已经被信息提取过的issue的关注点
     * @param issueId
     * @param csvId
     * @return
     */
    List<Focus> getMarkedIssueFocus(long issueId, long csvId);

    /**
     * 设置issue的关键词和关注点
     * @param issueId issue id
     * @param csvId csv id
     * @param focusList 关注点列表 还没有保存到数据库的focus
     * @param keywords 关键词列表 还没有保存到数据库的keyword
     * @param userId 用户id
     * @return 是否设置成功
     */
    boolean setIssueKeywordsAndFocus(long issueId, long csvId, List<Focus> focusList, List<Keyword> keywords, long userId);

    /**
     * 新建issue的关键词和关注点
     * @param issueId issue id
     * @param csvId csv id
     * @param focusList 关注点列表 还没有保存到数据库的focus
     * @param keywords 关键词列表 还没有保存到数据库的keyword
     * @return 是否设置成功
     */
    boolean initIssueKeywordsAndFocus(long issueId, long csvId, List<Focus> focusList, List<Keyword> keywords);

    /**
     * 获得已经被信息提取过的issue的关键词
     * @param issueId
     * @param csvId
     * @return
     */
    List<Keyword> getMarkedIssueKeyword(long issueId, long csvId);

    /**
     * 当前用户对当前issue的哪些关键词投过票
     * @param issueId
     * @param csvId
     * @param userId
     * @Return
     * */
    public List<Keyword> getKeywordByUserIdAndIssueId(long issueId, long csvId, long userId);

    /**
     * 当前用户对当前issue的哪些关注点投过票
     * @param issueId
     * @param csvId
     * @param userId
     * @Return
     * */
    public List<Focus> getFocusByUserIdAndIssueId(long issueId, long csvId, long userId);
}
