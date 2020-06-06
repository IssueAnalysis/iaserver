package com.issue.iaserver.data.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

/**
 * 投票接口
 * User: 钟镇鸿
 * Date: 2020/6/6
 * Time: 15:52
 * Description:
 */
public interface VoteService {

    /*
     * 这个类把四种对应关系放在一起
     * IssueID 关键词id 投票用户id（这个用来标识那些用户给哪个issue的哪个关键词投过票）
     * IssueID 关注点id 投票用户id（这个用来标识那些用户给哪个issue的哪个关注点投过票）
     * IssueID 关键词id 票数（issue的某个关键词的票数）
     * IssueID 关注点id 票数（issue的某个关注点的票数）
     *
     * 有空值，根据对应关系设置
     */
    boolean addFocusVote(long csv_id, long issue_id, long focus_id, long user_id, int vote);

    boolean addKeywordVote(long csv_id, long issue_id, long keyword_id, long user_id, int vote);

    ArrayList<Long> getFocusByUserId(long csv_id, long item_id, long user_id);

    ArrayList<Long> getKeywordByUserId(long csv_id, long item_id, long user_id);

    ArrayList<Integer> getFocusVote(long csv_id, long item_id, long focus_id);

    ArrayList<Integer> getKeywordVote(long csv_id,  long item_id, long focus_id);
    ArrayList<Long> getKeyWordIdByIssueID( long csv_id, long item_id);

    ArrayList<Long> getFocusIdByIssueID( long csv_id, long item_id);
}
