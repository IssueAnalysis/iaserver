package redis.service.impl;
import redis.service.PublishTemplateService;
import util.JsonSerializer;
import org.springframework.stereotype.Component;

/**
 * Redis 发布订阅 -相关操作
 *
 * @author 钟镇鸿
 */
@Component
public class PublishTemplateServiceImpl extends BaseTemplateImpl implements PublishTemplateService {

    @Override
    public Long publish(String key, Object value) {
        validateParam(key, value);
        return redisClient.invoke(jedisPool, jedis -> {
            return jedis.publish(key.getBytes(), JsonSerializer.serialize(value));
        });
    }

}
