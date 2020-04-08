package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.webserver.model.Issue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/issue")

public class IssueController {


    @RequestMapping("/find_all")
    public List<Issue> findAll(){

        Issue issue1 = new Issue("s1", "d1", "i1", "c1");
        Issue issue2 = new Issue("s2", "d2", "i2", "c2");
        Issue issue3 = new Issue("s3", "d3", "i3", "c3");

        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);

        return issues;
    }


    @PostMapping("/post_file")
    public String uploadFile(@RequestParam("issues") MultipartFile multipartFile) {
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getSize());
        return "";
    }
}
