package webserver.controller;

import webserver.model.Issue;
import webserver.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webserver.service.impl.test;

import java.util.List;


@RestController
@RequestMapping("/api/issue")
public class IssueController {

    private final
    IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping("/find_all")
    public List<Issue> findAll() {

        return issueService.getAllIssues();

    }


    @PostMapping("/post_file")
    public String uploadFile(@RequestParam("issues") MultipartFile multipartFile) {
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getSize());

        return "";
    }


}
