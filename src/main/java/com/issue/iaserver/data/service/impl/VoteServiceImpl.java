package com.issue.iaserver.data.service.impl;

import com.issue.iaserver.data.mysql.dao.VoteDao;
import com.issue.iaserver.data.mysql.entity.VoteDO;
import com.issue.iaserver.data.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/6/6
 * Time: 15:52
 * Description:
 */
@Service("VoteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteDao voteDao;

    @Override
    public boolean addFocusVote(long csv_id, long issue_id, long focus_id, long user_id, int vote) {
        //long id, long csv_id, long item_id, long focus_id, long keyword_id, long user_id, int vote
        VoteDO voteDO = new VoteDO(0, csv_id, issue_id, focus_id, -1, user_id, vote);
        voteDao.saveAndFlush(voteDO);
        return true;
    }

    @Override
    public boolean addKeywordVote(long csv_id, long issue_id, long keyword_id, long user_id, int vote) {
        VoteDO voteDO = new VoteDO(0, csv_id, issue_id, -1, keyword_id, user_id, vote);
        voteDao.saveAndFlush(voteDO);
        return true;
    }

    @Override
    public ArrayList<Long> getFocusByUserId(long csv_id, long item_id, long user_id) {
        return voteDao.getFocusByUserId(csv_id, item_id, user_id);
    }

    @Override
    public ArrayList<Long> getKeywordByUserId(long csv_id, long item_id, long user_id) {
        return voteDao.getKeywordByUserId(csv_id, item_id, user_id);
    }

    @Override
    public ArrayList<Integer> getFocusVote(long csv_id, long item_id, long focus_id) {
        return voteDao.getFocusVote(csv_id, item_id, focus_id);
    }

    @Override
    public ArrayList<Integer> getKeywordVote(long csv_id, long item_id, long keyword_id) {
        return voteDao.getKeywordVote(csv_id, item_id, keyword_id);
    }

    @Override
    public ArrayList<Long> getKeyWordIdByIssueID(long csv_id, long item_id) {
        return voteDao.getKeyWordIdByIssueID(csv_id, item_id);
    }

    @Override
    public ArrayList<Long> getFocusIdByIssueID(long csv_id, long item_id) {
        return voteDao.getFocusIdByIssueID(csv_id, item_id);
    }
    /*
 * 这个类把四种对应关系放在一起
 * IssueID 关键词id 投票用户id（这个用来标识那些用户给哪个issue的哪个关键词投过票）
 * IssueID 关注点id 投票用户id（这个用来标识那些用户给哪个issue的哪个关注点投过票）
 * IssueID 关键词id 票数（issue的某个关键词的票数）
 * IssueID 关注点id 票数（issue的某个关注点的票数）
 *
 * 有空值，根据对应关系设置
 */



}
