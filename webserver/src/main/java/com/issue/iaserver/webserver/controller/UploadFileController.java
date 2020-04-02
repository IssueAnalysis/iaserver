package com.issue.iaserver.webserver.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import java.io.InputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/file/")
public class UploadFileController {

    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getSize());
        return "";
    }
}
