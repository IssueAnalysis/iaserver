package com.issue.iaserver.nlp.wnl;

public interface WnlService {
    double getSimilarityByPath(String inputWord, String inputTag, String targetWord, String targetTag);

    double getSimilarityByLin(String inputWord, String inputTag, String targetWord, String targetTag);

    int getEditDistance(String input, String target);
}