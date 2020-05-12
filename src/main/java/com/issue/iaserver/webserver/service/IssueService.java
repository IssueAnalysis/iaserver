package com.issue.iaserver.webserver.service;

import com.issue.iaserver.webserver.model.Issue;
import com.issue.iaserver.webserver.model.IssueBrief;

import java.util.List;


public interface IssueService {

    //得到所有issue
    public List<IssueBrief> getAllIssues();

    //得到所有我上传的issue
    public List<IssueBrief> getAllAddedIssues(long user_id);

    public List<IssueBrief> getAllCollectedIssues(long user_id);

    //得到issue详细信息
    public Issue getIssueDetail(long issue_id, long csv_id);

}
