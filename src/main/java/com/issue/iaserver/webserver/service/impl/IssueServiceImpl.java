package com.issue.iaserver.webserver.service.impl;

import com.issue.iaserver.data.mongodb_es.CSVitem;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.format.service.Formatter;
import com.issue.iaserver.webserver.model.Issue;
import com.issue.iaserver.webserver.model.IssueBrief;
import com.issue.iaserver.webserver.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<IssueBrief> getAllIssues() {
        List<CSVDO> csvdos = operateFileService.getAllCSV();
        return csvdos.parallelStream()
                .map(x -> operateFileService.getCSVitemByCSVid(x.getId()))
                .flatMap(Collection::stream)
                .map(this::getIssueBriefFromCSVItem)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssueBrief> getAllAddedIssues(long user_id) {
        List<CSVDO> csvdos = operateFileService.getCSVByUser(user_id);
        return csvdos.parallelStream()
                .map(x -> operateFileService.getCSVitemByCSVid(x.getId()))
                .flatMap(Collection::stream)
                .map(this::getIssueBriefFromCSVItem)
                .collect(Collectors.toList());

    }

    @Override
    public List<IssueBrief> getAllCollectedIssues(long user_id) {
        List<CSVitem> csvItems = operateFileService.getCSVitemByUeserInCollect(user_id);
        return csvItems.parallelStream()
                .map(this::getIssueBriefFromCSVItem)
                .collect(Collectors.toList());
    }

    @Override
    public Issue getIssueDetail(long id, long csv_id) {
        List<CSVitem> csvItems = operateFileService.getCSVitemByCSVid(csv_id);
        CSVitem csvItem = null;
        for(CSVitem i : csvItems){
            if(i.getId() == id) {
                csvItem = i;
                break;
            }
        }
        if(csvItem == null)
            return null;
        // TODO待做
        return getIssueFromCSVItem(csvItem);
    }

    private Issue getIssueFromCSVItem(CSVitem csvItem) {
//        System.out.println(csvItem.getDescription());
        Issue issue = new Issue(csvItem);
        String description = formatter.format(issue.getDescription());
        issue.setDescription(description);
        issue.setBriefDescription(formatter.getBriefDescription(description));
        issue.setRichDescription(formatter.getRichDescription(description));
        return issue;
    }

    private IssueBrief getIssueBriefFromCSVItem(CSVitem csvItem) {
        String formattedDesc = formatter.format(csvItem.getDescription());
        String briefDesc = formatter.getBriefDescription(formattedDesc);

        return new IssueBrief(csvItem.getId(),
                csvItem.getCSVid(),
                csvItem.getSummary(),
                briefDesc);
    }
}
