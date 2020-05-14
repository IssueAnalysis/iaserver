package com.issue.iaserver.format.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class SentenceDetector {

    private SentenceModel model;
    private SentenceDetectorME detector;


    public SentenceDetector() throws IOException {
        InputStream inputStream = new FileInputStream("src/main/resources/models/en-sent.bin");
        model = new SentenceModel(inputStream);
        detector = new SentenceDetectorME(model);
    }

    public Span[] detect(String s) {
        //检测句子的位置
        Span[] spans = detector.sentPosDetect(s);

        return spans;

        //Printing the sentences and their spans of a paragraph
//        for (Span span : spans)
//            System.out.println(s.substring(span.getStart(), span.getEnd()) + " " + span);

    }


    public static void main(String args[]) throws IOException {

        String s = "abcc.\n\n\naac.";
        String s2 = "mmmdkd,sd. edee .";
        SentenceDetector d = new SentenceDetector();
        d.detect(s);
        d.detect(s2);


    }
}