package com.issue.iaserver.extractor.server;

import com.issue.iaserver.extractor.dao.DaoController;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/24 11:01 下午
 */
@Service
public class Vote implements VoteService{

    private DaoController daoController;

    @Autowired
    public Vote(DaoController daoController) {
        this.daoController = daoController;
    }

    @Override
    public boolean voteFocus(long issueId, long csvId, long userId, long focusId) {
        return daoController.voteIssueFocus(issueId, csvId, userId, focusId);
    }

    @Override
    public boolean voteKeyword(long issueId, long csvId, long userId, long keywordId) {
        return daoController.voteIssueKeyword(issueId, csvId, userId, keywordId);
    }

    @Override
    public List<Focus> getVotedFocus(long issueId, long csvId, long userId) {
        return daoController.getVotedIssueFocus(issueId, csvId, userId);
    }

    @Override
    public List<Keyword> getVotedKeyword(long issueId, long csvId, long userId) {
        return daoController.getVotedIssueKeywords(issueId, csvId, userId);
    }
}
