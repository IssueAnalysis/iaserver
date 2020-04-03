package com.iaserver.data.redis.service;

import java.util.List;
import java.util.Map;

/**
 * Redis 管道查询 - Pipeline -相关操作
 *
 * @author 钟镇鸿
 * @since 2020/3/16 22:57
 */
public interface PipelineTemplateService {

    <T> List<T> zRevRangePipeline(String key, Class<T> tClass, List<Integer> starts);

    List<Map<String, String>> hgetallPipeline(List<String> hashKeys);

    List<String> hmgetallPipeline(List<String> hashKeys, String fieldKey);


}
