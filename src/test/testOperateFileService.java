import data.IaserverApplication;
import data.mongdb.CSVitem;
import data.mysql.dao.UserDao;
import data.mysql.entity.CSVDO;
import data.mysql.entity.UserDO;
import data.service.OperateFileService;
import data.service.impl.OperateFileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 16:31
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IaserverApplication.class)
public class testOperateFileService {

    @Autowired
    UserDao userDao;
    @Autowired
    OperateFileService o;

    @Test
    public void test1(){
        UserDO userDO = userDao.getOne((long)1);
        System.out.println(userDO.getId());
        o.uploadFile((long)1,"C:\\Users\\m1885\\Desktop\\test.csv");
    }

    @Test
    public void test2(){
        assertEquals(689,o.getCSVitemByCSVid((long)4434).size());
    }

    @Test
    public void test3(){
        assertEquals(2,o.getAllCSV().size());
    }

    @Test
    public void test4(){
        assertEquals(2,o.getCSVByUser((long)1).size());
    }

    @Test
    public void test5(){

        CSVitem csVitem = o.getCSVitemByCSVid((long)4434).get(0);
        csVitem.setConsideration("ok");
        csVitem.setIntension("log");
        o.updateCSV((long)4434, csVitem);

        CSVitem csVitem2 = o.getCSVitemByCSVid((long)4434).get(0);
        assertEquals("ok",csVitem2.getConsideration());
        assertEquals("log",csVitem2.getIntension());
    }
}
