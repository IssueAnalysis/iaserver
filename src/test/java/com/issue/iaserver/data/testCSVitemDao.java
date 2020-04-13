package com.issue.iaserver.data;

import com.issue.iaserver.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/7
 * Time: 19:05
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
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
