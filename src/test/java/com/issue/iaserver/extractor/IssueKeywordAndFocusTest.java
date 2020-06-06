package com.issue.iaserver.extractor;

import com.issue.iaserver.Main;
import com.issue.iaserver.extractor.keyword.Keyword;
import com.issue.iaserver.extractor.server.InfoExtractor;
import com.issue.iaserver.format.algorithm.MachineLearning;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/25 1:22 下午
 */
@SpringBootTest(classes = {Main.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class IssueKeywordAndFocusTest {

    @Autowired
    private InfoExtractor infoExtractor;

    private MachineLearning machineLearning = new MachineLearning();

    private String csvPath = "src/main/resources/datamini.csv";
    private int count = 10;

    public IssueKeywordAndFocusTest() throws IOException {
    }


    @Test
    public void testIssueExtractor(){
        List<String> descriptionList = machineLearning.getDescriptionsFromCsv(csvPath);
        if(descriptionList.size() < count) count = descriptionList.size();
        for(int i = 0; i < count; i++){
            String text = descriptionList.get(i);
            List<Keyword> keywords = infoExtractor.findKeyWords(text);
            System.out.println(text);
            for(Keyword keyword : keywords){
                System.out.println(keyword.getKeyword());
            }
        }
    }
}
