package com.iaserver.data.service.impl;

import com.aliyun.oss.OSSClient;
import com.iaserver.data.mongdb.DBOperation;
import com.iaserver.data.mongdb.MongoDBConnection;
import com.iaserver.data.mysql.dao.CSVitemDao;
import com.iaserver.data.mysql.entity.CSVitemDO;
import com.iaserver.data.mysql.entity.UserDO;
import com.iaserver.data.service.OperateFileService;
import com.iaserver.data.util.AliyunOSSClientUtil;
import com.iaserver.data.util.OSSClientConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 对csv文件进行操作的接口
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 16:28
 * Description:
 */
@Service("OperateFileService")
public class OperateFileServiceImpl implements OperateFileService {

    @Autowired
    public CSVitemDao csVitemDao;

    @Override
    public void uploadFile(UserDO user, String filePath){
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

        CSVitemDO csVitemDO = new CSVitemDO((long)0, url, user.getId(), user);
        //System.out.println(csVitemDO.getId());
        //System.out.println(csVitemDao == null);
        csVitemDao.save(csVitemDO);

        mongoClient.close();
    }

    @Override
    public ArrayList<Document> getFileContent(String url){
        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");

        String[] u = url.split("/");
        String collectionName = u[u.length-1];
        return dbOperation.selectData(mongoDatabase, url);
    }
}
