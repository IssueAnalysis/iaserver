package com.issue.iaserver.nlp.nlpUtil;

import com.issue.iaserver.Main;
import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
class LemmatizerTest {


    private final Lemmatizer lemmatizer;
    private final Detector detector;

    @Autowired
    LemmatizerTest(Detector detector,Lemmatizer lemmatizer) {
        this.lemmatizer = lemmatizer;
        this.detector = detector;
    }

    @Test
    void lemmatize() throws IOException, ModelNotFoundException {
        String text = new FileUtil().getTestText();
        String[] tokens = detector.detectTokens(text);
        String[] res = lemmatizer.lemmatize(text);
        int index = 0;
        for(String str: res){
            System.out.println(tokens[index] + " -> " + str);
            index++;
        }
    }
}