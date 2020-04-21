package com.issue.iaserver.webserver.service;

import com.issue.iaserver.webserver.model.Issue;

import java.util.List;


public interface IssueService {

    //得到所有issue
    public List<Issue> getAllIssues();

    //得到所有我上传的issue
    public List<Issue> getAllAddedIssues(long user_id);

    public List<Issue> getAllCollectedIssues(long user_id);
}
