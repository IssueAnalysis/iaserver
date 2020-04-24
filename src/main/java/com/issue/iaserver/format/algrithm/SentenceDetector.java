package com.issue.iaserver.format.algrithm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class SentenceDetector {

    public static void main(String args[]) {

        String sen = "* Clean up and standardize some of the logging\n" +
                " * Change some of logging from STDERR/STDIN to logging facilities\n" +
                " * Little bit of clean up\n";


                //Loading a sentence model
        SentenceModel model = null;
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/models/en-sent.bin");
            model = new SentenceModel(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //Instantiating the SentenceDetectorME class
        SentenceDetectorME detector = new SentenceDetectorME(model);

        //Detecting the position of the sentences in the paragraph
        Span[] spans = detector.sentPosDetect(sen);

        //Printing the sentences and their spans of a paragraph
        for (Span span : spans)
            System.out.println(sen.substring(span.getStart(), span.getEnd()) + " " + span);
    }
}