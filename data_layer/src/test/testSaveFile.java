import com.aliyun.oss.OSSClient;
import com.iaserver.data.IaserverApplication;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.iaserver.data.util.AliyunOSSClientUtil;
import com.iaserver.data.util.OSSClientConstants;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IaserverApplication.class)
public class testSaveFile {

    @Test
    public void testSave(){


        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        /* @param ossClient  oss连接
         * @param file 上传文件（文件全路径如：D:\\image\\cake.jpg）
         * @param bucketName  存储空间
         * @param folder 此为图片所在的文件夹，将它设置为requestid，如requestid为1——设为"1/"*/
        String res = AliyunOSSClientUtil.uploadObject2OSS(ossClient,
                "C:\\Users\\m1885\\Desktop\\my_csv.csv",
                OSSClientConstants.BACKET_NAME,
                "");
        Assertions.assertEquals("https://iaserver.oss-cn-hangzhou.aliyuncs.com/my_csv.csv", res);
    }

}
