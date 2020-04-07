import com.iaserver.data.IaserverApplication;
import com.iaserver.data.mysql.dao.UserDao;
import com.iaserver.data.mysql.entity.UserDO;
import com.iaserver.data.service.OperateFileService;
import com.iaserver.data.service.impl.OperateFileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        o.uploadFile(userDO,"C:\\Users\\m1885\\Desktop\\my_csv_simple2.csv");
    }
}
