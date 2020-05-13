package com.issue.iaserver.nlp.focus.wnl;

import com.issue.iaserver.Main;
import com.issue.iaserver.nlp.model.PosTag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
class SimilarityCalculatorTest {

    @Autowired
    private SimilarityCalculator similarityCalculator;
    @Test
    void computeByLin() {
        System.out.println(similarityCalculator.computeByLin("add", PosTag.VB.getName(),"pull",PosTag.VB.getName()));
    }

    @Test
    void computeByPath() {
    }
}