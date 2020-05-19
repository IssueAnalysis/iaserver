package com.issue.iaserver.extractor.keyword;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KeywordFrequencyTest {

    @Test
    public void KeywordFrequencyTest1(){
        // 测试添加freq
        KeywordFrequency keywordFrequency = new KeywordFrequency();
        keywordFrequency.addWord("abc");
        keywordFrequency.addWord("abd");
        keywordFrequency.addWord("abc");
        assertEquals(2,keywordFrequency.getFreq("abc"));
        assertEquals(1,keywordFrequency.getFreq("abd"));
        assertEquals(0,keywordFrequency.getFreq("this is a test"));
    }

    @Test
    public void KeywordFrequencyTest2(){
        // 测试持久化
        KeywordFrequency keywordFrequency = new KeywordFrequency();
        keywordFrequency.addWord("abc");
        keywordFrequency.addWord("abc");
        keywordFrequency.addWord("ace");
        keywordFrequency.addWord("bce");
        keywordFrequency.addWord("abd");
        keywordFrequency.updateData();
        KeywordFrequency keywordFrequency1 = new KeywordFrequency();
        assertEquals(2, keywordFrequency1.getFreq("abc"));
        assertEquals(1,keywordFrequency1.getFreq("abd"));
        assertEquals(0, keywordFrequency1.getFreq("this is a test"));
    }

}