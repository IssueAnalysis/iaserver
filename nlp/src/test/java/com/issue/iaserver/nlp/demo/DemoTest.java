package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DemoTest {

    Demo demo = new Demo();

    @Test
    void detectSentences() throws IOException, ModelNotFoundException {
        File file = new File(this.getClass().getResource("/").getPath() + "/testTexts");
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = bufferedInputStream.readAllBytes();
        String s = new String(bytes);
        String[] sentences = demo.detectSentences(s);
        for(String str : sentences){
            System.out.println(str);
        }
        bufferedInputStream.close();
    }
}