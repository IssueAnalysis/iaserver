package com.issue.iaserver.data.service.impl;

import com.issue.iaserver.data.lucene.LuceneSearch;
import com.issue.iaserver.data.lucene.LuceneSyn;
import com.issue.iaserver.data.mongodb.CSVitem;
import com.issue.iaserver.data.mongodb.DBOperation;
import com.issue.iaserver.data.mongodb.MongoDBConnection;
import com.issue.iaserver.data.mysql.dao.CSVDao;
import com.issue.iaserver.data.mysql.entity.CSVDO;
import com.issue.iaserver.data.service.OperateFileService;
import com.issue.iaserver.data.service.SearchService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索实现
 * User: 钟镇鸿
 * Date: 2020/5/14
 * Time: 19:17
 * Description:
 */
@Service("SearchService")
public class SearchServiceImpl implements SearchService {

    LuceneSyn luceneSyn = new LuceneSyn();
    LuceneSearch luceneSearch = new LuceneSearch();

    @Autowired
    public CSVDao csvDao;

    @Autowired
    public OperateFileService operateFileService;

    @Override
    public void syn() {
        List<CSVDO> csvdoList = csvDao.findAll();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");

        ArrayList<String> ids = new ArrayList<String>();
        for(CSVDO csvdo : csvdoList){
            ids.add(csvdo.getId()+"");
        }
        luceneSyn.Synchronization(mongoDatabase, ids);
    }

    @Override
    public ArrayList<CSVitem> search(String content) throws Exception {

        return luceneSearch.search(content);
    }
}
