package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.*;

class DetectorTest {

    Detector detector = new Detector();

    @Test
    void detectSentences() throws IOException, ModelNotFoundException {
        File file = new File(this.getClass().getResource("/").getPath() + "/testTexts");
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = bufferedInputStream.readAllBytes();
        String s = new String(bytes);
        String[] sentences = detector.detectSentences(s);
        String[] tokens = detector.detectTokens(s);
        String[] pos = detector.detectPos(s);
        String[] chunks = detector.detectChunker(s);
        for(String str : pos){
            System.out.println(str);
        }
        bufferedInputStream.close();
    }
}