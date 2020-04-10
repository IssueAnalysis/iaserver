package webserver.service.impl;

import webserver.model.Issue;
import webserver.service.IssueService;

import java.util.ArrayList;
import java.util.List;

public class IssueServiceImplMock implements IssueService {
    @Override
    public List<Issue> getAllIssues() {
        Issue issue1 = new Issue("s1", "d1", "i1", "c1");
        Issue issue2 = new Issue("s2", "d2", "i2", "c2");
        Issue issue3 = new Issue("s3", "d3", "i3", "c3");

        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);

        return issues;
    }

    @Override
    public void uploadFile(String filePath) {

    }
}
