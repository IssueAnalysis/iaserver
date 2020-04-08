package com.issue.iaserver.nlp.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.*;


public class FileUtil {
    public String getTestText() throws IOException {
        File file = new File(this.getClass().getResource("/").getPath() + "/testTexts");
        System.out.println(file.getPath());
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = bufferedInputStream.readAllBytes();
        String s = new String(bytes);
        bufferedInputStream.close();
        return s;
    }
}
