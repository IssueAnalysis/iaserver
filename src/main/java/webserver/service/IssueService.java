package webserver.service;

import webserver.model.Issue;

import java.util.List;

public interface IssueService {

    public List<Issue> getAllIssues();

    public void uploadFile(String filePath);

}
