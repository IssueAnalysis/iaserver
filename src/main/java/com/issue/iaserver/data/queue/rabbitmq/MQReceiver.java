package com.issue.iaserver.data.queue.rabbitmq;

import com.issue.iaserver.data.mongodb.CSVitem;
import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.data.service.RedisService;
import com.issue.iaserver.data.service.impl.RedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 接受者
 * User: 钟镇鸿
 * Date: 2020/4/15
 * Time: 21:13
 * Description:
 */
//@Service
public class MQReceiver {
    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    RedisService redisService;

    @Autowired
    OperateFileService operateFileService;

    @RabbitListener(queues=MQConfig.ISSUE_QUEUE)
    public CSVitem receive(String message) {
        log.info("receive message:"+message);
        MQMessage mm  = RedisServiceImpl.stringToBean(message, MQMessage.class);
        UserDO user = mm.getUser();
        long csvId = mm.getCsvId();
        long issueId = mm.getIssueId();

        List<CSVitem> csVitems = operateFileService.getCSVitemByCSVid(csvId);
        CSVitem csVitem = csVitems.get((int)issueId);
        return csVitem;
        //判断是否已经秒杀到了
        /*MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            return;
        }
        //减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);*/
    }
}
