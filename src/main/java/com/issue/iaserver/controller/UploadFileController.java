package com.issue.iaserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Map;

@Controller
@RequestMapping("/file/")
public class UploadFileController {

    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartRequest multipartRequest){
        Map<String, MultipartFile> multipartFileMap = multipartRequest.getFileMap();

        for(String str : multipartFileMap.keySet()){
            System.out.println(str);
        }

        return "success";
    }
}
