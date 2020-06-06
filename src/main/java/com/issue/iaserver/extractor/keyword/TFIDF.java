package com.issue.iaserver.extractor.keyword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TFIDF {


    private KeywordFrequency keywordFrequency;

    @Autowired
    public TFIDF(KeywordFrequency keywordFrequency) {
        this.keywordFrequency = keywordFrequency;
    }

    /**
     * 关键词进行排序，通过TF-IDF
     * @param textTokenNum 文本token数量
     * @param keywordList 关键词列表
     * @return 按照从大到小顺序对关键词排序
     */
    public List<String> getSortedTokens(int textTokenNum, List<String> keywordList) {
        HashMap<String,Integer> keywordNumHashMap = new HashMap<>();
        // keyword去重并计数
        for(String str : keywordList){
            if(keywordNumHashMap.containsKey(str)){
                keywordNumHashMap.put(str,keywordNumHashMap.get(str) + 1);
            }else{
                keywordNumHashMap.put(str, 1);
            }
        }
        List<KeywordWithTFIDF> keywordWithTFIDFS = new ArrayList<>();
        double tf;
        double idf;
        double tfIdf;
        for(String str : keywordNumHashMap.keySet()){
            tf = ((double)keywordNumHashMap.get(str)) / textTokenNum;
            idf = Math.log(((double)keywordFrequency.getTotalIssueCount()) / (keywordFrequency.getFreq(str) + 1));
            tfIdf = tf * idf;
            keywordWithTFIDFS.add(new KeywordWithTFIDF(str,tfIdf));
            keywordFrequency.addWord(str);
        }
        keywordWithTFIDFS.sort(KeywordWithTFIDF::compareTo);
        List<String> res = new ArrayList<>();
        for(KeywordWithTFIDF keywordWithTFIDF : keywordWithTFIDFS){
            System.out.println(keywordWithTFIDF.getTfIdf());
            res.add(keywordWithTFIDF.getKeyword());
        }
        return res;
    }
}
