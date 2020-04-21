package com.issue.iaserver.data.mongodb_es;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

/**
 * mongdb链接类
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 14:17
 * Description:
 */
public class MongoDBConnection {
    final String pass = "348FKYbcty";
    //链接数据库
    public MongoClient getConn2(){
        MongoClient mongoClient = new MongoClient("106.54.135.213",27017);

        //MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");
        //MongoClient mongoClient = new MongoClient(mongoClientURI);
        return mongoClient;
    }

    //通过用户密码认证链接数据库
    public MongoClient getConn(){
        String name = "ia2";//用户名

        String dbName = "iadb";//数据库名

        char [] pwd = pass.toCharArray();//用户密码(将字符串转换成字符数组)
        List<ServerAddress> addresses = new ArrayList<>();

        //服务器地址：链接地址,端口号
        ServerAddress address = new ServerAddress("106.54.135.213",27017);
        addresses.add(address);
        List<MongoCredential> credentials = new ArrayList<>();

        //认证方法需要三个参数，用户名，数据库名，用户密码
        MongoCredential credential = MongoCredential.createScramSha1Credential(name,dbName,pwd);
        credentials.add(credential);

        //创建链接对象
        MongoClient mongoClient = new MongoClient(addresses,credentials);
        return mongoClient;
    }

    public static void main(String [] args){
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");
        //获取数据库中的user集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("4450");
        //获取user集合中的文档
        FindIterable<Document> iterable = collection.find();
        //通过迭代器遍历找到的文档中的信息
        MongoCursor<Document> iterator = iterable.iterator();
        int count = 0;
        while(iterator.hasNext()) {
            count++;
            System.out.println(count);
        }
    }
}
