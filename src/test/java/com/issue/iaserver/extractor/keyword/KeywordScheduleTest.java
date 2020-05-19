package com.issue.iaserver.extractor.keyword;

import com.issue.iaserver.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
public class KeywordScheduleTest {

    @Autowired
    KeywordFrequency keywordFrequency;

    @Test
    public void test() throws InterruptedException {
        keywordFrequency.addWord("abc");
        KeywordFrequency keywordFrequency1 = new KeywordFrequency();
        assertEquals(0,keywordFrequency1.getFreq("abc"));
        Thread.sleep(70000);
        keywordFrequency1 = new KeywordFrequency();
        assertEquals(1, keywordFrequency1.getFreq("abc"));
    }
}
