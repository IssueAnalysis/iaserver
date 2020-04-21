package com.issue.iaserver.webserver.service.impl;

import com.issue.iaserver.data.mongdb.CSVitem;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.format.service.Formatter;
import com.issue.iaserver.webserver.model.Issue;
import com.issue.iaserver.webserver.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    private final
    OperateFileService operateFileService;

    private final Formatter formatter;

    @Autowired
    public IssueServiceImpl(OperateFileService operateFileService, Formatter formatter) {
        this.operateFileService = operateFileService;
        this.formatter = formatter;
    }

    @Override
    public List<Issue> getAllIssues() {
        List<CSVDO> csvdos = operateFileService.getAllCSV();
        return csvdos.parallelStream()
                    .map(x -> operateFileService.getCSVitemByCSVid(x.getId()))
                    .flatMap(Collection::stream)
                    .map(this::getIssueFromCSVItem).collect(Collectors.toList());
    }

    @Override
    public List<Issue> getAllAddedIssues(long user_id) {

        List<Issue> issues = new ArrayList<>();
        List<CSVDO> csvdos = operateFileService.getCSVByUser(user_id);
        for (CSVDO csvdo : csvdos) {
            List<CSVitem> csVitems = operateFileService.getCSVitemByCSVid(csvdo.getId());
            issues.addAll(getIssueListFromCSVItemList(csVitems));
        }
        return issues;
    }

    @Override
    public List<Issue> getAllCollectedIssues(long user_id) {
        List<CSVitem> csVitems = operateFileService.getCSVitemByUeserInCollect(user_id);
        return getIssueListFromCSVItemList(csVitems);
    }

    private List<Issue> getIssueListFromCSVItemList(List<CSVitem> csvItems){
        return csvItems.parallelStream().map(this::getIssueFromCSVItem).collect(Collectors.toList());
    }

    private Issue getIssueFromCSVItem(CSVitem csvItem){
//        System.out.println(csvItem.getDescription());
        Issue issue = new Issue(csvItem);
        String description = formatter.format(issue.getDescription());
        issue.setDescription(description);
        System.out.println(description.indexOf('\n'));
        issue.setBriefDescription(formatter.getBriefDescription(description));
        issue.setRichDescription(formatter.getRichDescription(description));
        return issue;
    }
}
