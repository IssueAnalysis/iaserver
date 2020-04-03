package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.model.Models;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.IOException;

public class Demo {
    public static void main(String[] args){
        System.out.println("yeah");
    }

    private Models models;

    public Demo(){
        models = new Models();
    }


    public String[] detectSentences(String text) throws ModelNotFoundException, IOException {
        SentenceModel sentenceModel = models.loadSentenceModel();
        SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(sentenceModel);
        return sentenceDetectorME.sentDetect(text);
    }

}
