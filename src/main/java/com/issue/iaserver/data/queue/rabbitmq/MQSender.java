package com.issue.iaserver.data.queue.rabbitmq;

import com.issue.iaserver.data.service.impl.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息队列发送
 * User: 钟镇鸿
 * Date: 2020/4/15
 * Time: 21:12
 * Description:
 */
@Service
public class MQSender {

    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate ;

    public void sendMiaoshaMessage(MQMessage mm) {
        String msg = RedisServiceImpl.beanToString(mm);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.ISSUE_QUEUE, msg);
    }
}
