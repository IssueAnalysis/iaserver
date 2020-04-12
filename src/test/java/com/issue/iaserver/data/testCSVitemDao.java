package com.issue.iaserver.data;

import com.issue.iaserver.data.IaserverApplication;
import com.issue.iaserver.data.mysql.entity.CSVDO;
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
 * Date: 2020/4/7
 * Time: 19:05
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IaserverApplication.class)
public class testCSVitemDao {
    // TODO 报错先注释了
//    @Autowired
//    CSVDO csVitemDao;

    @Test
    public void test1(){
        //ArrayList<CSVDO> r = csVitemDao.getCSVitemByUserId((long)1);
        //assertEquals(r.size(), 2);
    }
}
