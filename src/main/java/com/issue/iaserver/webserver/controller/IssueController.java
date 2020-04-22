package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.data.mongodb_es.CSVitem;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.webserver.model.Issue;
import com.issue.iaserver.webserver.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    private final OperateFileService operateFileService;

    private final IssueService issueService;

    /*@Autowired
    public SearchService searchService;*/
    @Autowired
    public IssueController(OperateFileService operateFileService, IssueService issueService) {
        this.operateFileService = operateFileService;
        this.issueService = issueService;
    }


    @RequestMapping("/find_all")
    public List<Issue> findAll() {
        return issueService.getAllIssues();
    }

    @RequestMapping("/find_add")
    public List<Issue> findAdd(HttpSession session) {
        long user_id = (long)session.getAttribute("user_id");
        return issueService.getAllAddedIssues(user_id);

    }

    @RequestMapping("/find_collect")
    public List<Issue> findCollect(HttpSession session) {
        long user_id = (long)session.getAttribute("user_id");
        return issueService.getAllCollectedIssues(user_id);

    }

    @PostMapping("/post_file")
    public boolean postIssuesByFile(@RequestParam("file") MultipartFile multipartFile, HttpSession session) {
        System.out.println("in post file");
        if (multipartFile == null)
            return false;
        String filename = multipartFile.getOriginalFilename();
        System.out.println("fileName: "+ filename );
        if (filename == null)
            return false;
        long user_id = (long) session.getAttribute("user_id");
//        long user_id = 1;
        String prefix = String.valueOf(System.currentTimeMillis());
        String suffix = filename.substring(filename.lastIndexOf("."));
        try {
            File file = File.createTempFile(prefix, suffix);
            multipartFile.transferTo(file);
            operateFileService.uploadFile(user_id, file.getPath());
            if (file.exists())
                file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("upload file succeeded!!!");
        return true;
    }

    /*@PostMapping("list")
    public @ResponseBody Page<CSVitem> list(Integer pageNumber, Integer pageSize, String searchContent) {
        return searchService.searchIssuePage(pageNumber, pageSize, searchContent);
    }*/
}
