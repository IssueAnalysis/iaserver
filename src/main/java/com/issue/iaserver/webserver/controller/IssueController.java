package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.extractor.server.VoteService;
import com.issue.iaserver.webserver.model.Issue;
import com.issue.iaserver.webserver.model.IssueBrief;
import com.issue.iaserver.webserver.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/issue")
public class IssueController {

    private final OperateFileService operateFileService;

    private final IssueService issueService;

    private final VoteService voteService;

    /*@Autowired
    public SearchService searchService;*/
    @Autowired
    public IssueController(VoteService voteService,OperateFileService operateFileService, IssueService issueService) {
        this.operateFileService = operateFileService;
        this.issueService = issueService;
        this.voteService = voteService;
    }


    @RequestMapping("/find_all")
    public List<IssueBrief> findAll() {
        return issueService.getAllIssues();
    }

    @RequestMapping("/find_add")
    public List<IssueBrief> findAdd(HttpSession session) {
        long user_id = (long)session.getAttribute("user_id");
        return issueService.getAllAddedIssues(user_id);

    }

    @RequestMapping("/find_collect")
    public List<IssueBrief> findCollect(HttpSession session) {
        long user_id = (long)session.getAttribute("user_id");
        return issueService.getAllCollectedIssues(user_id);

    }

    @GetMapping("/detail")
    public Issue getDetailedIssue(HttpSession session,@RequestParam("id") long id, @RequestParam("csv_id") long csv_id){
        return issueService.getIssueDetail(id, csv_id, (long)session.getAttribute("user_id"));
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

    @PostMapping("/vote_keyword")
    public boolean voteKeyword(HttpSession httpSession,
                               @RequestParam("issue_id")long issueId,
                               @RequestParam("csv_id")long csvId,
                               @RequestParam("keyword_id")long keywordId){
        long userId = (long)httpSession.getAttribute("user_id");
        return voteService.voteKeyword(issueId,csvId,userId,keywordId);
    }

    @PostMapping("/vote_focus")
    public boolean voteFocus(HttpSession httpSession,
                             @RequestParam("issue_id") long issueId,
                             @RequestParam("csv_id") long csvId,
                             @RequestParam("focus_id")long focusId){
        long userId = (long) httpSession.getAttribute("user_id");
        return voteService.voteFocus(issueId,csvId,userId,focusId);
    }
}
