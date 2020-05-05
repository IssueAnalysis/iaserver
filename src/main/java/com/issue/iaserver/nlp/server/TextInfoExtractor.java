package com.issue.iaserver.nlp.server;

import com.issue.iaserver.nlp.keyword.TFIDF;
import com.issue.iaserver.nlp.nlpUtil.Detector;
import com.issue.iaserver.nlp.nlpUtil.Lemmatizer;
import com.issue.iaserver.nlp.nlpUtil.NameFinder;
import com.issue.iaserver.nlp.focus.Focus;
import com.issue.iaserver.nlp.focus.FocusController;
import com.issue.iaserver.nlp.keyword.Keyword;
import com.issue.iaserver.nlp.pojos.PosTagDic;
import com.issue.iaserver.nlp.focus.wnl.WnlService;
import opennlp.tools.util.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TextInfoExtractor implements InfoExtractor{

    private NameFinder nameFinder;
    private Detector detector;
    private Lemmatizer lemmatizer;
    private PosTagDic posTagDic;
    private FocusController focusController;
    private WnlService wnlService;
    private TFIDF tfidf;

    private final Logger logger = LoggerFactory.getLogger(TextInfoExtractor.class);

    @Autowired
    public TextInfoExtractor(FocusController focusController,
                             PosTagDic posTagDic,
                             Lemmatizer lemmatizer,
                             Detector detector,
                             NameFinder nameFinder,
                             WnlService wnlService,
                             TFIDF tfidf) {
        this.focusController = focusController;
        this.detector = detector;
        this.nameFinder = nameFinder;
        this.lemmatizer = lemmatizer;
        this.posTagDic = posTagDic;
        this.wnlService = wnlService;
        this.tfidf = tfidf;
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
    public String[] findKeyWords(String textIdentify,String text) {
        // nlp进行处理
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
        // 使用TF-IDF进行排序
        List<String> sortedKeyWords = tfidf.getSortedTokens(textIdentify,tokens.length,keywordList);
        return keywordList.toArray(new String[sortedKeyWords.size()]);
    }


    @Override
    public List<Focus> findIssueFocus(List<Keyword> keywords) {
        List<Focus> focusList = focusController.getAllFocus();
        for(Focus focus : focusList){
            // 计算关注点权值
            List<Keyword> focusKeyWords = focus.getKeywordList();
            int count = 0;
            boolean isNext = false;
            for(Keyword keyword : keywords){
                // 是否完全相等
                isNext = false;
                for(Keyword focusKeyWord : focusKeyWords){
                    if(keyword.getKeyword().equals(focusKeyWord.getKeyword())) {
                        count++;
                        isNext = true;
                        break;
                    }
                }
                if(isNext) continue;
                // 是否编辑距离适合
                for(Keyword focusKeyWord : focusKeyWords){
                    if(wnlService.getEditDistance(keyword.getKeyword(),focusKeyWord.getKeyword()) <= 3){
                        count++;
                        isNext = true;
                        break;
                    }
                }
                if(isNext) continue;
                // 是否有同义词
                for(Keyword focusKeyWord : focusKeyWords){
                    if(wnlService.getSimilarityByLin(
                            keyword.getKeyword(),
                            keyword.getPosTag(),
                            focusKeyWord.getKeyword(),
                            focusKeyWord.getPosTag()
                    ) > 0.3){
                        count++;
                        break;
                    }
                }
            }
            focus.setCount(count);
        }
        focusList.sort(Comparator.naturalOrder());
        return focusList;

    }

    private void markSpansToEmpty(String[] token, Span[] spans){
        for(Span span : spans){
            for(int i = span.getStart(); i < span.getEnd(); i++){
                token[i] = "";
            }
        }
    }
}
