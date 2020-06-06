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
        String str = " This is wrong and misleading, maybe we can improve the pattern like: *Moved BLOCK complete, copied from PROXY DN.";
        String[] sentences = detector.detectSentences(str);
        String[] tokens = detector.detectTokens(str);
        String[] pos = detector.detectPos(str);
        String[] chunks = detector.detectChunker(str);
        Span[] spans = detector.getChunkSpan(str);
        System.out.print("{");
        for(String ss : pos){
            System.out.print("\"" + ss + "\", ");
        }
        System.out.print("}");
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