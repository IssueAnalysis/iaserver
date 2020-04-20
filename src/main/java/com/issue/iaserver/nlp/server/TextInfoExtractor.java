package com.issue.iaserver.nlp.server;

import com.issue.iaserver.nlp.demo.Detector;
import com.issue.iaserver.nlp.demo.Lemmatizer;
import com.issue.iaserver.nlp.demo.NameFinder;
import com.issue.iaserver.nlp.focus.FocusController;
import com.issue.iaserver.nlp.focus.Focus;
import com.issue.iaserver.nlp.focus.Keyword;
import com.issue.iaserver.nlp.pojos.PosTagDic;
import opennlp.tools.util.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TextInfoExtractor implements InfoExtractor{

    private NameFinder nameFinder;
    private Detector detector;
    private Lemmatizer lemmatizer;
    private PosTagDic posTagDic;
    private FocusController focusController;

    private final Logger logger = LoggerFactory.getLogger(TextInfoExtractor.class);

    @Autowired
    public TextInfoExtractor(FocusController focusController,
                             PosTagDic posTagDic,
                             Lemmatizer lemmatizer,
                             Detector detector,
                             NameFinder nameFinder) {
        this.focusController = focusController;
        this.detector = detector;
        this.nameFinder = nameFinder;
        this.lemmatizer = lemmatizer;
        this.posTagDic = posTagDic;
    }

    private String[] removeUselessTokens(String text){
        String[] tokens = detector.detectTokens(text);
        String[] pos = detector.detectPos(text);
        String[] lemmatizeTokens = lemmatizer.lemmatize(tokens, pos);
        List<String> filteredTokens = new ArrayList<>();
        for(int i = 0; i < tokens.length; i++){
            if(posTagDic.isNoun(pos[i]) || posTagDic.isVerb(pos[i])){
                filteredTokens.add(lemmatizeTokens[i]);
            }
        }
        return filteredTokens.toArray(new String[filteredTokens.size()]);
    }


    @Override
    public String[] findKeyWords(String text) {
        String[] tokens = removeUselessTokens(text);
        markSpansToEmpty(tokens, nameFinder.findTimes(tokens));
        markSpansToEmpty(tokens, nameFinder.findDates(tokens));
        markSpansToEmpty(tokens, nameFinder.findLocations(tokens));
        markSpansToEmpty(tokens, nameFinder.findMoneys(tokens));
        markSpansToEmpty(tokens, nameFinder.findOrganizations(tokens));
        markSpansToEmpty(tokens, nameFinder.findPersons(tokens));
        markSpansToEmpty(tokens, nameFinder.findPercentages(tokens));
        List<String> keywordList = new ArrayList<>();
        for(String str: tokens){
            if(str.length() != 0
            && !str.equals("O")
            && !str.startsWith("{")
            && !str.startsWith("}")){
                keywordList.add(str);
            }
        }
        return keywordList.toArray(new String[keywordList.size()]);
    }


    @Override
    public List<Focus> findIssueFocus(List<Keyword> keywords) {
        List<Focus> focusList = focusController.getAllFocus();
        

        return null;

    }

    private void markSpansToEmpty(String[] token, Span[] spans){
        for(Span span : spans){
            for(int i = span.getStart(); i < span.getEnd(); i++){
                token[i] = "";
            }
        }
    }
}
