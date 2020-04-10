package webserver.service.impl;

import data.mongdb.CSVitem;
import data.mysql.entity.CSVDO;
import data.service.OperateFileService;
import webserver.model.Issue;
import webserver.service.IssueService;
import com.opencsv.CSVReader;
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
                Issue issue = new Issue(item.getSummary(), item.getDescription(), item.getIntension(), item.getConsideration());
                issues.add(issue);
            }
        }
        return issues;
    }

}
