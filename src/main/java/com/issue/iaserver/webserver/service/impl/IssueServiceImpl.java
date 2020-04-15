package com.issue.iaserver.webserver.service.impl;

import com.issue.iaserver.data.mongdb.CSVitem;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.webserver.model.Issue;
import com.issue.iaserver.webserver.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final OperateFileService operateFileService;

    @Autowired
    public IssueServiceImpl(OperateFileService operateFileService) {
        this.operateFileService = operateFileService;
    }

    @Override
    public List<Issue> getAllIssues() {
        List<CSVDO> csvdos = operateFileService.getAllCSV();

        List<Issue> issues = new ArrayList<>();
        for(CSVDO csvdo : csvdos){
            long csvId = csvdo.getId();
            List<CSVitem> csvItems = operateFileService.getCSVitemByCSVid(csvId);
            for(CSVitem item : csvItems){
                Issue issue = new Issue(item);
                issues.add(issue);
            }
        }
        return issues;
    }

    @Override
    public void uploadFile(String filePath) {


    }

}
