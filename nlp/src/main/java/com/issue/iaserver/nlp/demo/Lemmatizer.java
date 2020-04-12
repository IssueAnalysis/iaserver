package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.model.Models;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Lemmatizer {


    private final Detector detector;
    private final Models models;

    @Autowired
    public Lemmatizer(Detector detector, Models models) {
        this.models = models;
        this.detector = detector;
    }

    public String[] lemmatize(String text) throws ModelNotFoundException, IOException {
        String[] tokens = detector.detectTokens(text);
        String[] pos = detector.detectPos(text);
        return this.lemmatize(tokens, pos);
    }

    public String[] lemmatize(String[] tokens,String[] pos) throws ModelNotFoundException, IOException {
        DictionaryLemmatizer dictionaryLemmatizer = models.loadLemmatizer();
        String[] afterLTokens = dictionaryLemmatizer.lemmatize(tokens, pos);
        int index = 0;
        for(String str: tokens){
            if(afterLTokens[index].equals("O")){
                afterLTokens[index] = str;
            }
            index++;
        }
        return afterLTokens;
    }
}
