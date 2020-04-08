package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.util.FileUtil;
import opennlp.tools.util.Span;
import org.junit.jupiter.api.Test;

import java.io.*;

class DetectorTest {

    Detector detector = new Detector();

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