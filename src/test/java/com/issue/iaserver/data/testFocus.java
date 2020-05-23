package com.issue.iaserver.data;

import com.issue.iaserver.Main;
import com.issue.iaserver.data.mysql.dao.MarkDao;
import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.service.FocusService;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/5/21
 * Time: 10:54
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class testFocus {

    @Autowired
    FocusService focusService;

    @Test
    public void test1(){
        //boolean setIssueKeywordsAndFocus(long issueId, long csvId, List<Focus> focusList, List<Keyword> keywords, long userId);
        List<Focus> focusList = new ArrayList<Focus>();
        List<Keyword> keywords = new ArrayList<Keyword>();

        //public Keyword(String keyword, int vote)
        Keyword keyword1 = new Keyword("keyword test1", 0);
        Keyword keyword2 = new Keyword("keyword test2", 0);
        Keyword keyword3 = new Keyword("keyword test3", 0);
        Keyword keyword4 = new Keyword("keyword test4", 0);
        ArrayList<Keyword> keywords1 = new ArrayList<Keyword>();
        keywords1.add(keyword1);
        keywords1.add(keyword2);
        keywords1.add(keyword3);
        keywords1.add(keyword4);

        //Focus(long id, String focusDescription, List<Keyword> keywordList, String focusType)
        Focus focus = new Focus(1, "focus test 1", keywords1, "日志考虑因素");
        focusList.add(focus);

        focusService.setIssueKeywordsAndFocus(0, 4465, focusList, keywords, 1);
    }

    @Test
    public void test2(){
        boolean is = focusService.isIssueExtracted((long)1, (long)4465);
        assertEquals(false, is);
    }

    @Test
    public void test3(){
        boolean is = focusService.isIssueExtracted(0, 4465);
        assertEquals(true, is);
    }

    @Test
    public void test4(){
        List<FocusDO> focuss = focusService.getAllFocus();
        FocusDO focusDO = focuss.get(1);
        List<Keyword> keywords = focusDO.getKeywordList();
        assertEquals("keyword test5", keywords.get(1).getKeyword());
    }
}
