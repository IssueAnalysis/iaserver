package com.issue.iaserver.extractor.keyword;

public class KeywordWithTFIDF implements Comparable<KeywordWithTFIDF>{
    private String keyword;
    private double tfIdf;

    public KeywordWithTFIDF(String keyword, double tfIdf) {
        this.keyword = keyword;
        this.tfIdf = tfIdf;
    }

    public String getKeyword() {
        return keyword;
    }

    public double getTfIdf() {
        return tfIdf;
    }

    @Override
    public int compareTo(KeywordWithTFIDF o) {
        return Double.compare(o.tfIdf,this.tfIdf);
    }
}
