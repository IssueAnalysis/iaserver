package com.issue.iaserver.data.service.impl;

import com.issue.iaserver.data.mongodb.CSVitem;
import com.issue.iaserver.data.mongodb.DBOperation;
import com.issue.iaserver.data.mongodb.MongoDBConnection;
import com.issue.iaserver.data.mysql.dao.FocusDao;
import com.issue.iaserver.data.mysql.dao.MarkDao;
import com.issue.iaserver.data.mysql.dao.UserDao;
import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.mysql.entity.MarkDO;
import com.issue.iaserver.data.service.FocusService;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.MarkTaintedMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现关注点
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 19:43
 * Description:
 */
@Service("FocusService")
public class FocusServiceImpl implements FocusService {

    @Autowired
    public FocusDao focusDao;
    @Autowired
    public MarkDao markDao;

    /**获取全部的Focus*/
    @Override
    public List<FocusDO> getAllFocus() {
        return focusDao.findAll();
    }

    /**
     * 当前issue是否已经被信息提取过
     * @param issueId issue id
     * @param csvId csv id，和issueId到一起作为issue的唯一标识
     * @return 是否被提取过
     */
    @Override
    public boolean isIssueExtracted(long issueId, long csvId) {
        ArrayList<MarkDO> markDOS = markDao.getMarkById(csvId+"", issueId+"");
        if(markDOS.size() == 1) return true;

        return false;
    }

    /**
     * 将当前一个issue标记为已被提取过
     * @param issueId issue id
     * @param csvId csv id， 和issueId一起作为issue的唯一标识
     * @return 是否标记成功
     */
    @Override
    public boolean markIssueExtracted(long issueId, long csvId) {
        /*long csv_id, long item_id, boolean isMarked*/
        MarkDO markDO = new MarkDO(0, csvId, issueId, true);
        if(isIssueExtracted(issueId, csvId)){
            markDao.saveAndFlush(markDO);
            return true;
        }
        return false;
    }

    /**
     * 根据issue的Id 和 csv Id 获得已经标记过的issue的关键词
     * @param issueId issue id
     * @param csvId csv id
     * @return issue的关键词
     */
    @Override
    public List<Keyword> getMarkedIssueKeywords(long issueId, long csvId) {
        List<Keyword> keywords = new ArrayList<Keyword>();
        if(!isIssueExtracted(issueId, csvId)){
            return keywords;
        }
        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        MongoClient mongoClient = mongoDBConnection.getConn();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");

        CSVitem csVitem = dbOperation.getItem(mongoDatabase, csvId, issueId);


        return null;
    }

    /**
     * 设置issue的关键词和关注点
     * @param issueId issue id
     * @param csvId csv id
     * @param focusList 关注点列表
     * @param keywords 关键词列表
     * @return 是否设置成功
     */
    @Override
    public boolean setIssueKeywordsAndFocus(long issueId, long csvId, List<Focus> focusList, List<Keyword> keywords) {
        return false;
    }

    /**
     * 获得已经被信息提取过的issue的关注点
     * @param issueId
     * @param csvId
     * @return
     */
    @Override
    public List<Focus> getMarkedIssueFocus(long issueId, long csvId) {
        return null;
    }

    /**
     * 获得已经被信息提取过的issue的关键词
     * @param issueId
     * @param csvId
     * @return
     */
    @Override
    public List<Keyword> getMarkedIssueKeyword(long issueId, long csvId) {
        return null;
    }
}
