package com.issue.iaserver.data.util;
import java.util.UUID;

/**
 * 分布式session id
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 19:47
 * Description:
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}

