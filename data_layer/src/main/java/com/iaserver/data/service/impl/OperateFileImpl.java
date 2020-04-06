package com.iaserver.data.service.impl;

import com.aliyun.oss.OSSClient;
import com.iaserver.data.mongdb.DBOperation;
import com.iaserver.data.mongdb.MongoDBConnection;
import com.iaserver.data.service.OperateFile;
import com.iaserver.data.util.AliyunOSSClientUtil;
import com.iaserver.data.util.OSSClientConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 16:28
 * Description:
 */
public class OperateFileImpl implements OperateFile {

    @Override
    public void uploadFile(String filePath){
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        String url = AliyunOSSClientUtil.uploadObject2OSS(ossClient,
                filePath,
                OSSClientConstants.BACKET_NAME,
                "");

        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");

        String name = dbOperation.insertData(mongoDatabase,url);
        System.out.println("插入数据后：");
        dbOperation.selectData(mongoDatabase, name);

        mongoClient.close();
    }
}
