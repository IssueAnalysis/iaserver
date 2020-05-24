package com.issue.iaserver.extractor.server;

import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;

import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/24 8:40 下午
 */
public interface VoteService {
    boolean voteFocus(long issueId, long csvId, long userId, long focusId);

    boolean voteKeyword(long issueId, long csvId, long userId, long keywordId);

    List<Focus> getVotedFocus(long issueId, long csvId, long userId);

    List<Keyword> getVotedKeyword(long issueId, long csvId, long userId);
}
