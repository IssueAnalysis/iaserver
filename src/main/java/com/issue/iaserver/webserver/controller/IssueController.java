package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.data.mongdb.CSVitem;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.webserver.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired
    public OperateFileService operateFileService;

    @RequestMapping("/find_all")
    public List<Issue> findAll() {

        List<Issue> issues = new ArrayList<>();
        List<CSVDO> csvdos = operateFileService.getAllCSV();
        for(CSVDO csvdo : csvdos){
            List<CSVitem> csVitems = operateFileService.getCSVitemByCSVid(csvdo.getId());
            for(CSVitem csVitem : csVitems){
                issues.add(new Issue(csVitem));
            }
        }
        return issues;

    }

    @RequestMapping("/find_add")
    public List<Issue> findAdd(@RequestParam String user_id) {

        List<Issue> issues = new ArrayList<>();
        List<CSVDO> csvdos = operateFileService.getCSVByUser(Long.valueOf(user_id));
        for(CSVDO csvdo : csvdos){
            List<CSVitem> csVitems = operateFileService.getCSVitemByCSVid(csvdo.getId());
            for(CSVitem csVitem : csVitems){
                issues.add(new Issue(csVitem));
            }
        }
        return issues;

    }

    @RequestMapping("/find_collect")
    public List<Issue> findCollect(@RequestParam String user_id) {

        List<Issue> issues = new ArrayList<>();

        List<CSVitem> csVitems = operateFileService.getCSVitemByUeserInCollect(Long.valueOf(user_id));
        for(CSVitem csVitem : csVitems){
            issues.add(new Issue(csVitem));
        }
        return issues;

    }

    @PostMapping("/post_file")
    public String postIssuesByFile(HttpSession session, @RequestParam("csv_file_path") String filePath) {
        operateFileService.uploadFile((Long) session.getAttribute("user_id"),filePath);

        return "";
    }
}
