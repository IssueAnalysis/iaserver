package com.issue.iaserver.nlp.server;

public interface InfoExtractor {

    /**
     * 获得所有的关键词
     * @param text 文章
     * @return token数组中的log相关关键词
     */
    String[] findKeyWords(String text);

}
