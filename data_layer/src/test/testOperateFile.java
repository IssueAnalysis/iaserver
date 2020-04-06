import com.iaserver.data.IaserverApplication;
import com.iaserver.data.service.OperateFile;
import com.iaserver.data.service.impl.OperateFileImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class testOperateFile {

    @Test
    public void test1(){
        OperateFile o = new OperateFileImpl();
        o.uploadFile("C:\\Users\\m1885\\Desktop\\my_csv_simple2.csv");
    }
}
