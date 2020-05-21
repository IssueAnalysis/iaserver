package com.issue.iaserver.data;

import com.issue.iaserver.Main;
import com.issue.iaserver.data.mongodb.CSVitem;
import com.issue.iaserver.data.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/5/14
 * Time: 19:21
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class testSearch {

    @Autowired
    SearchService searchService;

    @Test
    public void test1(){
        searchService.syn();
    }

    @Test
    public void test2() throws Exception {
        ArrayList<CSVitem> res = searchService.search("Bug");
        assertEquals(res.size(), 308);
    }
}
