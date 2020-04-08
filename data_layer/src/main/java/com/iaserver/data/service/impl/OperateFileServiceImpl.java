package com.iaserver.data.service.impl;

import com.aliyun.oss.OSSClient;
import com.iaserver.data.mongdb.DBOperation;
import com.iaserver.data.mongdb.MongoDBConnection;
import com.iaserver.data.mysql.dao.CSVDao;
import com.iaserver.data.mysql.dao.UserDao;
import com.iaserver.data.mysql.entity.CSVDO;
import com.iaserver.data.mysql.entity.UserDO;
import com.iaserver.data.service.OperateFileService;
import com.iaserver.data.util.AliyunOSSClientUtil;
import com.iaserver.data.util.OSSClientConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.lettuce.core.dynamic.annotation.Param;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public UserDao userDao;
    @Autowired
    public CSVDao csvDao;

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

        CSVDO csvDO = new CSVDO(0, url, "", "", user.getId(), user);
        csvDao.saveAndFlush(csvDO);
        long csvId = csvDO.getId();

        String name = dbOperation.insertData(mongoDatabase, csvId, url);
        System.out.println("插入数据后：");
        dbOperation.selectData(mongoDatabase, name);

        mongoClient.close();
    }

    @Override
    public List<Document> getDocumentByCSVid(long csvId){
        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");

        ArrayList<Document> document = dbOperation.selectData(mongoDatabase, csvId+"");

        return document;
    }

    /**获取全部csv文件*/
    @Override
    public List<CSVDO> getAllCSV() {
        List<CSVDO> csvdo = csvDao.findAll();
        return csvdo;
    }

    /**通过用户id获取csv文件，也就是用户上传的csv文件*/
    @Override
    public List<CSVDO> getCSVByUser(long userid) {
        List<CSVDO> csvdo = csvDao.getCSVByUserId(userid);
        return csvdo;
    }

    /**通过内容获取csv文件*/
    @Override
    public List<CSVDO> getCSVByText(String content) {
        List<CSVDO> csvdos = getAllCSV();
        List<CSVDO> res = new ArrayList<CSVDO>();
        for(CSVDO csvdo : csvdos) {
            List<Document> documents = getDocumentByCSVid( csvdo.getId());
            //System.out.println(documents.toString());
            String d = documents.toString();
            if(d.contains(content)){
                res.add(csvdo);
            }
        }
        return res;
    }

    /**修改csv文件的intension，consideration*/
    @Override
    public CSVDO updateCSV(CSVDO csvdo) {
        csvDao.saveAndFlush(csvdo);
        return csvdo;
    }
}