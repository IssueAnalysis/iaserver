package com.issue.iaserver.data.bfbor.utils;


import com.issue.iaserver.data.bfbor.base.BfConfiguration;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class BFUtil {
    private BfConfiguration conf = null;
    private Jedis jedis;
    private final int MAX_SIZE;
    private final int HASH_TIMES;
    private final String BLOOM_NAME = "BLOOM_BITS";
    //private final Logger logger = Logger.getLogger(BFUtil.class);

    public BFUtil(BfConfiguration conf){
        this.conf = conf;
        this.MAX_SIZE = conf.getBitLength();
        this.HASH_TIMES = conf.getHashNumber();
        jedis = new Jedis(conf.getHost(),conf.getPort(),2000);
    }

    public boolean add(String str){
        if (!jedis.exists(BLOOM_NAME)){
            jedis.setbit(BLOOM_NAME,MAX_SIZE,false);
        }
        List<Integer> hashs = toHashs(str);
        if (isExist(hashs)){
            //判断在集合中是否存在
            return false;
        }else {
            //如果不存在，将对应的hash位置为1
            for (int hash:
                 hashs) {
                jedis.setbit(BLOOM_NAME,hash,true);
            }
            return true;
        }
    }

    public boolean isExist(List<Integer> hashs){
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
