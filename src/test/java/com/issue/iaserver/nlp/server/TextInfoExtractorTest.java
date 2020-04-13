package com.issue.iaserver.nlp.server;

import com.issue.iaserver.Main;
import com.issue.iaserver.nlp.demo.Detector;
import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.pojos.PosTagDic;
import com.issue.iaserver.nlp.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
class TextInfoExtractorTest {

    @Autowired
    private InfoExtractor infoExtractor;
    @Autowired
    private Detector detector;

    @Autowired
    private PosTagDic posTagDic;

    @Test
    void findKeyWords() throws IOException {
        String text =new FileUtil().getTestText();
        String[] keywords = infoExtractor.findKeyWords(text);
        System.out.println("Token数量为："+ detector.detectTokens(text).length);
        System.out.println("关键词数量为： " + keywords.length);
        String[] pos = detector.detectPos(keywords);
        int index = 0;
        for(String str: keywords){
            System.out.println(str + " " + pos[index]);
            index++;
        }
    }

    @Test
    void testPosDic(){
        System.out.println(posTagDic.isNoun("IN"));
        System.out.println(posTagDic.isVerb("IN"));
    }
}