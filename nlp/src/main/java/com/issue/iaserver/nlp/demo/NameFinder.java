package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.model.Models;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class NameFinder {


    private final Models models;

    @Autowired
    public NameFinder(Models models) {
        this.models = models;
    }

    public String[] findTimes(String[] tokens) throws ModelNotFoundException, IOException {
        TokenNameFinderModel tokenNameFinderModel = models.loadTimeNameFinder();
        NameFinderME nameFinderME = new NameFinderME(tokenNameFinderModel);
        Span[] timeTokenSpans = nameFinderME.find(tokens);
        return null;
    }
}
