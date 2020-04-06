import com.iaserver.data.IaserverApplication;
import com.iaserver.data.mysql.entity.UserDO;
import com.iaserver.data.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IaserverApplication.class)
public class testLogin {
    @Autowired
    UserService userService;
    @Test
    public void testLogin(){
        UserDO user = userService.login((long)2, "123456");
        assertEquals(user.getName(), "B");
    }
}
