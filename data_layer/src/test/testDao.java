import com.iaserver.data.IaserverApplication;
import com.iaserver.data.mysql.dao.CSVitemDao;
import com.iaserver.data.mysql.dao.UserDao;
import com.iaserver.data.mysql.entity.CSVitemDO;
import com.iaserver.data.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/7
 * Time: 14:33
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IaserverApplication.class)
public class testDao {

    @Autowired
    public UserDao user;
    @Autowired
    public CSVitemDao csVitemDao;

    @Test
    public void test1(){
        user.getOne((long)1);
    }

    @Test
    public void test2(){
        CSVitemDO csVitemDO = new CSVitemDO((long)1,"ajskldjaklsdkalsdjklasjdklasljklasjdkl", (long)1, user.getOne((long)1));
        csVitemDao.saveAndFlush(csVitemDO);
    }
}
