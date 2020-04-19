package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.data.mongdb.CSVitem;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.webserver.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
        List<CSVDO> csvdos = operateFileService.getCSVByUser(Long.parseLong(user_id));
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

        List<CSVitem> csVitems = operateFileService.getCSVitemByUeserInCollect(Long.parseLong(user_id));
        for(CSVitem csVitem : csVitems){
            issues.add(new Issue(csVitem));
        }
        return issues;

    }

    @PostMapping("/post_file")
    public boolean postIssuesByFile(@RequestBody MultipartFile multipartFile, HttpSession session) {
        if(multipartFile == null)
            return false;
        String filename = multipartFile.getOriginalFilename();
        if(filename == null)
            return false;
        long user_id = (long) session.getAttribute("user_id");
        String prefix = String.valueOf(System.currentTimeMillis());
        String suffix = filename.substring(filename.lastIndexOf("."));
        try {
            File file = File.createTempFile(prefix, suffix);
            multipartFile.transferTo(file);
            operateFileService.uploadFile(user_id, file.getPath());
            if(file.exists())
                file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
