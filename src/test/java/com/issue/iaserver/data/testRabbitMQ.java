package com.issue.iaserver.data;

import com.issue.iaserver.Main;
import com.issue.iaserver.data.mysql.dao.UserDao;
import com.issue.iaserver.data.queue.rabbitmq.MQMessage;
import com.issue.iaserver.data.queue.rabbitmq.MQReceiver;
import com.issue.iaserver.data.queue.rabbitmq.MQSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 19:49
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class testRabbitMQ {
    @Autowired
    MQSender mqSender;
    @Autowired
    UserDao userDao;
    @Autowired
    MQReceiver mqReceiver;

    @Test
    public void test1(){
        MQMessage mm = new MQMessage();
        mm.setCsvId(4450);
        mm.setIssueId(1);

        mm.setUser(userDao.getOne((long)1));
        mqSender.sendMiaoshaMessage(mm);
        mqReceiver.receive("mm");
    }

}
