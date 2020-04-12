package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.ApplicationRunner;
import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.util.FileUtil;
import opennlp.tools.util.Span;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ApplicationRunner.class})
class DetectorTest {


    private final Detector detector;

    @Autowired
    DetectorTest(Detector detector) {
        this.detector = detector;
    }

    @Test
    @SuppressWarnings("all")
    void detectSentences() throws IOException, ModelNotFoundException {
        String s = new FileUtil().getTestText();
        String[] sentences = detector.detectSentences(s);
        String[] tokens = detector.detectTokens(s);
        String[] pos = detector.detectPos(s);
        String[] chunks = detector.detectChunker(s);
        Span[] spans = detector.getChunkSpan(s);
        System.out.println(tokens.length);

        for(String str : tokens){
            System.out.println(str.toString());
        }
    }
}