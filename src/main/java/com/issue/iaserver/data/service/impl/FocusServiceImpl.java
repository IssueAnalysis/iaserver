package com.issue.iaserver.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.issue.iaserver.data.mysql.dao.*;
import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.mysql.entity.KeywordDO;
import com.issue.iaserver.data.mysql.entity.MarkDO;
import com.issue.iaserver.data.mysql.entity.VoteDO;
import com.issue.iaserver.data.service.FocusService;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public KeywordDao keywordDao;
    @Autowired
    public MarkDao markDao;
    @Autowired
    public VoteDao voteDao;

    /**获取全部的Focus*/
    @Override
    public List<FocusDO> getAllFocus() {
        return focusDao.findAll();
    }

    /**新增一个Focus*/
    @Override
    public boolean addFocus(Focus focus) {
        FocusDO focusDO = new FocusDO(focus);
        List<Keyword> keywords = focus.getKeywordList();
        for(Keyword Keyword : keywords) {
            KeywordDO keywordDO = new KeywordDO(Keyword);
            keywordDao.saveAndFlush(keywordDO);
        }
        focusDao.saveAndFlush(focusDO);
        return true;
    }

    @Override
    public boolean updateFocus(Focus focus){
        List<FocusDO> focusDOS = focusDao.findAll();
        for(FocusDO focusDO : focusDOS){
            if(focusDO.getId() == focus.getId()){
                FocusDO focusDO1 = new FocusDO(focus);
                long id = focus.getId();
                focusDO1.setId(id);
                focusDao.saveAndFlush(focusDO);
                return true;
            }
        }
        return false;
    }

    /**
     * 当前issue是否已经被信息提取过
     * @param issueId issue id
     * @param csvId csv id，和issueId到一起作为issue的唯一标识
     * @return 是否被提取过
     */
    @Override
    public boolean isIssueExtracted(long issueId, long csvId) {
        ArrayList<MarkDO> markDOS = markDao.getMarkById(csvId, issueId);
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
        List<KeywordDO> keywordIds = keywordDao.getKeywordByIssueId(csvId, issueId);
        for(KeywordDO keywordDO : keywordIds){
            Keyword keyword = new Keyword(keywordDO.getKeyword_description(), keywordDO.getVote());
            keywords.add(keyword);
        }
        return keywords;
    }

    /**
     * 设置issue的关键词和关注点，这个focus和keyword作为参数，但是是修改票数或者是其中的内容
     * @param issueId issue id
     * @param csvId csv id
     * @param focusList 关注点列表
     * @param keywords 关键词列表
     * @param userId 用户id
     * @return 是否设置成功
     */
    @Override
    public boolean setIssueKeywordsAndFocus(long issueId, long csvId,
                                            List<Focus> focusList, List<Keyword> keywords,
                                            long userId) {

        for (Focus focus : focusList) {
            List<Keyword> keywords1 = focus.getKeywordList();
            for(Keyword keyword : keywords1){
                KeywordDO keywordDO = new KeywordDO(keyword, csvId, issueId);
                keywordDao.saveAndFlush(keywordDO);
                System.out.println("[INFO] :关注点对应的关键词id是"+keywordDO.getId());
            }

            FocusDO focusDO = new FocusDO(focus, csvId, issueId);
            focusDao.saveAndFlush(focusDO);
            System.out.println("[INFO] :关注点id是"+focusDO.getId());

            VoteDO voteDO = new VoteDO(focusDO, userId);
            voteDao.saveAndFlush(voteDO);
        }
        return true;
    }

    @Override
    public boolean initIssueKeywordsAndFocus(long issueId, long csvId,
                                             List<Focus> focusList, List<Keyword> keywords) {
        for (Keyword keyword : keywords) {
            KeywordDO keywordDO = new KeywordDO(keyword, csvId, issueId);
            keywordDao.saveAndFlush(keywordDO);
            System.out.println("[INFO] :新增的关键词id是"+keywordDO.getId());
        }

        for (Focus focus : focusList) {
            List<Keyword> keywords1 = focus.getKeywordList();
            for(Keyword keyword : keywords1){
                KeywordDO keywordDO = new KeywordDO(keyword, csvId, issueId);
                keywordDao.saveAndFlush(keywordDO);
                System.out.println("[INFO] :关注点对应的关键词id是"+keywordDO.getId());
            }

            FocusDO focusDO = new FocusDO(focus, csvId, issueId);
            focusDao.saveAndFlush(focusDO);
            System.out.println("[INFO] :新增的关注点id是"+focusDO.getId());
        }
        return true;
    }

    /**
     * 获得已经被信息提取过的issue的关注点
     */
    @Override
    public List<Focus> getMarkedIssueFocus(long issue_id, long csv_id) {

        List<Focus> res = new ArrayList<Focus>();
        List<Long> focusDOS = voteDao.getFocusIdByIssueID(csv_id, issue_id);
        if(focusDOS.size() == 0) return res;
        for(Long focusID : focusDOS){
            FocusDO focusDO = focusDao.getOne(focusID);
            int vote = sum(voteDao.getFocusVote(csv_id, issue_id, focusDO.getId()));
            Focus focus = new Focus(focusDO);
            res.add(focus);
        }
        return res;
    }

    /**
     * 获得已经被信息提取过的issue的关键词
     * @param issueId
     * @param csvId
     * @return
     */
    @Override
    public List<Keyword> getMarkedIssueKeyword(long issueId, long csvId) {

        List<Keyword> res = new ArrayList<Keyword>();
        List<KeywordDO> keywordDOS = keywordDao.getKeywordByIssueId(csvId, issueId);
        for(KeywordDO keywordDO : keywordDOS){
            int vote = sum(voteDao.getKeywordVote(csvId, issueId, keywordDO.getId()));
            res.add(new Keyword(keywordDO.getKeyword_description(), vote));
        }
        return res;
    }

    /**
     * 当前用户对当前issue的哪些关注点和关键词投过票
     * voteDO中保存着对应的focus和keyword的id
     * @param issueId
     * @param csvId
     * @param userId
     * */
    public List<Keyword> getKeywordByUserIdAndIssueId(long issueId, long csvId, long userId){
        List<Keyword> keywordList = new ArrayList<Keyword>();
        List<Long> keywordIds = voteDao.getKeywordByUserId(csvId, issueId, userId);
        for(Long keywordId : keywordIds){
            KeywordDO keywordDO = keywordDao.getOne(keywordId);
            Keyword keyword = new Keyword(keywordDO.getKeyword_description(), keywordDO.getVote());
            keywordList.add(keyword);
        }
        return keywordList;
    }

    /**
     * 当前用户对当前issue的哪些关注点投过票
     * @param issueId
     * @param csvId
     * @param userId
     * @Return
     * */
    public List<Focus> getFocusByUserIdAndIssueId(long issueId, long csvId, long userId){
        List<Focus> focusList = new ArrayList<Focus>();
        List<Long> focusIds = voteDao.getFocusByUserId(csvId, issueId, userId);
        for(Long focusId : focusIds){
            FocusDO focusDO = focusDao.getOne(focusId);
            Focus focus = new Focus(focusDO);
            focusList.add(focus);
        }
        return focusList;
    }

    private int sum(ArrayList<Integer> focusVote) {
        int sum = 0;
        for(Integer integer : focusVote){
            sum += integer;
        }
        return sum;
    }
}
