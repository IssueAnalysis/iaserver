package com.issue.iaserver.extractor.nlpUtil;

import com.issue.iaserver.Main;
import com.issue.iaserver.extractor.model.ModelNotFoundException;
import com.issue.iaserver.extractor.util.FileUtil;
import opennlp.tools.util.Span;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
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

        for(String str : pos){
            System.out.println(str.toString());
        }
    }

    @Test
    void detectPos() throws IOException {
        String s = new FileUtil().getTestText();
        String[] pos = detector.detectPos(s);
        String[] tokens = detector.detectTokens(s);
        for(int i = 0; i < pos.length; i++){
            System.out.println(pos[i] + ":" + tokens[i]);
        }
    }
}