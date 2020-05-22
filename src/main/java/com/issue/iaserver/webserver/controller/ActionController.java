package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.webserver.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * 对应控制台的action接口
 * User: 钟镇鸿
 * Date: 2020/5/20
 * Time: 18:39
 * Description:
 */
@RestController
@RequestMapping("/api/action")
public class ActionController {

    private final IssueService issueService;

    @Autowired
    public ActionController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/collect_issues")
    public boolean collectIssues(@RequestParam("csv_id") long csv_id, @RequestParam("issue_id") long id,
                                 HttpSession session) {
        long user_id = (long) session.getAttribute("user_id");
        issueService.collectIssue(id, csv_id, user_id);
        return true;
    }
}
