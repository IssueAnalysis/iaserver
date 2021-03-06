package com.issue.iaserver.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.issue.iaserver.data.redis.KeyPrefix;
import com.issue.iaserver.data.service.RedisService;
import com.issue.iaserver.data.util.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过这个service来提供redis 的服务
 * User: 钟镇鸿
 * Date: 2020/4/17
 * Time: 18:20
 */
@Service("RedisService")
public class RedisServiceImpl implements RedisService{

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取当个对象
     * */
    @Override
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t =  stringToBean(str, clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     * */
    @Override
    public <T> boolean set(KeyPrefix prefix, String key,  T value) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0) {
                return false;
            }
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            int seconds =  prefix.expireSeconds();
            if(seconds <= 0) {
                jedis.set(realKey, str);
            }else {
                jedis.setex(realKey, seconds, str);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     * */
    public <T> boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.exists(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除
     * */
    @Override
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            long ret =  jedis.del(realKey);
            return ret > 0;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * */
    public <T> Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.incr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * */
    public <T> Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key
            String realKey  = prefix.getPrefix() + key;
            return  jedis.decr(realKey);
        }finally {
            returnToPool(jedis);
        }
    }

    public boolean delete(KeyPrefix prefix) {
        if(prefix == null) {
            return false;
        }
        List<String> keys = scanKeys(prefix.getPrefix());
        if(keys==null || keys.size() <= 0) {
            return true;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(keys.toArray(new String[0]));
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> scanKeys(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> keys = new ArrayList<String>();
            String cursor = "0";
            ScanParams sp = new ScanParams();
            sp.match("*"+key+"*");
            sp.count(100);
            do{
                ScanResult<String> ret = jedis.scan(cursor, sp);
                List<String> result = ret.getResult();
                if(result!=null && result.size() > 0){
                    keys.addAll(result);
                }
                //再处理cursor
                cursor = ret.getStringCursor();
            }while(!cursor.equals("0"));
            return keys;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    public static <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return ""+value;
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    /**转化成bean存进去*/
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    private final int MAX_SIZE = 15;
    private final int HASH_TIMES = 7;
    private final String BLOOM_NAME = "BLOOM_BITS";

    public boolean add(String str){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();

            if (!jedis.exists(BLOOM_NAME)) {
                jedis.setbit(BLOOM_NAME, MAX_SIZE, false);
            }
            List<Integer> hashs = toHashs(str);
            if (isExist(hashs, jedis)) {
                //判断在集合中是否存在
                return false;
            } else {
                //如果不存在，将对应的hash位置为1
                for (int hash :
                        hashs) {
                    jedis.setbit(BLOOM_NAME, hash, true);
                }
                return true;
            }
        }finally {
            returnToPool(jedis);
        }
    }

    public boolean isExist(List<Integer> hashs, Jedis jedis){
        boolean flag = true;
        for (int i = 0;(i < HASH_TIMES) && (flag)  ; i++) {
            flag = flag && jedis.getbit(BLOOM_NAME,hashs.get(i));
        }
        return flag;
    }


    public List<Integer> toHashs(String url){
        List<Integer> list = new ArrayList<Integer>();
        list.add(HashUtils.additiveHash(url, 47) % MAX_SIZE);
        list.add(HashUtils.rotatingHash(url, 47) % MAX_SIZE);
        list.add(HashUtils.oneByOneHash(url) % MAX_SIZE);
        list.add(HashUtils.bernstein(url) % MAX_SIZE);
        list.add(HashUtils.FNVHash(url.getBytes()) % MAX_SIZE);
        list.add(HashUtils.RSHash(url) % MAX_SIZE);
        list.add(HashUtils.JSHash(url) % MAX_SIZE);
        list.add(HashUtils.PJWHash(url) % MAX_SIZE);
        list.add(HashUtils.ELFHash(url) % MAX_SIZE);
        list.add(HashUtils.BKDRHash(url) % MAX_SIZE);
        list.add(HashUtils.SDBMHash(url) % MAX_SIZE);
        list.add(HashUtils.DJBHash(url) % MAX_SIZE);
        list.add(HashUtils.DEKHash(url) % MAX_SIZE);
        list.add(HashUtils.APHash(url) % MAX_SIZE);
        list.add(HashUtils.java(url) % MAX_SIZE);
        for (int i = 0; i < list.size(); i++) {
            list.set(i,Math.abs(list.get(i)));
        }
        return list;
    }
}
