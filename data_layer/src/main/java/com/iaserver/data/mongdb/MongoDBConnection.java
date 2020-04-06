package com.iaserver.data.mongdb;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.*;

/**
 * mongdb链接类
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 14:17
 * Description:
 */
public class MongoDBConnection {
    //链接数据库
    public MongoClient getConn(){
        MongoClient mongoClient = new MongoClient("106.54.135.213",27017);

        //MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");
        //MongoClient mongoClient = new MongoClient(mongoClientURI);
        return mongoClient;
    }

    //通过用户密码认证链接数据库
    public MongoClient getConnByCredit(){
        String name = "test";//用户名

        String dbName = "iadb";//数据库名

        char [] pwd = "test".toCharArray();//用户密码(将字符串转换成字符数组)
        List<ServerAddress> addresses = new ArrayList<>();

        //服务器地址：链接地址,端口号
        ServerAddress address = new ServerAddress("localhost",27017);
        addresses.add(address);
        List<MongoCredential> credentials = new ArrayList<>();

        //认证方法需要三个参数，用户名，数据库名，用户密码
        MongoCredential credential = MongoCredential.createScramSha1Credential(name,dbName,pwd);
        credentials.add(credential);

        //创建链接对象
        MongoClient mongoClient = new MongoClient(addresses,credentials);
        return mongoClient;
    }
}
