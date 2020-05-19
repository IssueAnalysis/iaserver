package com.issue.iaserver.data.service.impl;

import com.aliyun.oss.OSSClient;
import com.issue.iaserver.data.mongodb.CSVitem;
import com.issue.iaserver.data.mongodb.DBOperation;
import com.issue.iaserver.data.mongodb.MongoDBConnection;
import com.issue.iaserver.data.mysql.dao.CSVDao;
import com.issue.iaserver.data.mysql.dao.CollectDao;
import com.issue.iaserver.data.mysql.dao.UserDao;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.mysql.entity.CollectDO;
import com.issue.iaserver.data.mysql.entity.UserDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.data.util.AliyunOSSClientUtil;
import com.issue.iaserver.data.util.OSSClientConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    public CollectDao collectDao;

    @Override
    public void uploadFile(long user_id, String filePath){
        OSSClient ossClient = AliyunOSSClientUtil.getOSSClient();
        String url = AliyunOSSClientUtil.uploadObject2OSS(ossClient,
                filePath,
                OSSClientConstants.BACKET_NAME,
                "");

        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");

        UserDO user = userDao.getOne(user_id);
        CSVDO csvDO = new CSVDO(0, url, user.getId(), user);
        csvDao.saveAndFlush(csvDO);
        long csvId = csvDO.getId();

        String name = dbOperation.insertData(mongoDatabase, csvId, url);
        System.out.println("插入数据后：");
        dbOperation.selectData(mongoDatabase, name);

        mongoClient.close();
    }


    /**通过csvid来获取list item*/
    @Override
    public List<CSVitem> getCSVitemByCSVid(long csvId){
        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");

        ArrayList<CSVitem> csVitems = dbOperation.selectData(mongoDatabase, csvId+"");
        return csVitems;
    }

    /**获取全部csv文件*/
    @Override
    public List<CSVDO> getAllCSV(){
        return csvDao.findAll();
    }

    /**通过用户id获取csv，也就是用户上传的csv文件*/
    @Override
    public List<CSVDO> getCSVByUser(long userid){
        List<CSVDO> csvdo = csvDao.getCSVByUserId(userid);
        return csvdo;
    }

    /**修改csv文件的intension，consideration*/
    @Override
    public void updateCSV(long csv_id, CSVitem csVitem){
        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");
        dbOperation.updateData(mongoDatabase, csv_id, csVitem.getId(), csVitem.getIntension(), csVitem.getConsideration());
    }

    @Override
    /**通过userid，csvid,itemid 获取issue列表*/
    public List<CSVitem> getCSVitemByUeserInCollect(long user_id){
        List<CollectDO> collectDOS = collectDao.getCollectByUserId(user_id);

        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");


        List<CSVitem> csVitems = new ArrayList<CSVitem>();
        //双重循环
        for(CollectDO collectDO : collectDOS){
            ArrayList<CSVitem> items = dbOperation.selectData(mongoDatabase, collectDO.getCsv_id()+"");
            for(CSVitem csVitem : items){
                if(csVitem.getId() == collectDO.getItem_id()){
                    csVitems.add(csVitem);
                }
            }
        }
        return csVitems;
    }


    /**通过内容获取csv文件*/
   /* @Override
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
    }*/
}