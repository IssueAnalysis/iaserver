package com.issue.iaserver.extractor.dao;

import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.service.FocusService;
import com.issue.iaserver.data.service.VoteService;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/16 1:03 下午
 *
 * 用来对接数据层
 */
@Service("DaoController")
public class DaoController {


    @Resource(name = "FocusService")
    private FocusService focusService;

    private VoteService voteService;

    public DaoController(VoteService voteService,FocusService focusService) {
        this.focusService = focusService;
        this.voteService = voteService;
    }

    /**
     * 获得所有的关注点，指的是关注点统计信息中的关注点
     * @return 关注点列表
     */
    public List<Focus> getAllFocus(){
        List<FocusDO> focusDOS = focusService.getAllFocus();
        List<Focus> focusList = new ArrayList<>();
        for(FocusDO focusDO : focusDOS){
            focusList.add(new Focus(focusDO));
        }
        return focusList;
    }


    /**
     * 统计信息中是否有关注点，按照关注点的描述查找
     * @param focus 关注点
     * @return 是否查找到关注点
     */
    public boolean isStatisticHasFocus(Focus focus){
        List<FocusDO> focus1 = focusService.getAllFocus();
        for(FocusDO focusDO : focus1){
            if(focusDO.getFocusDescription().equals(focus.getFocusDescription()))
                return true;
        }
        return false;
    }

    /**
     * 在统计信息中添加关注点
     * @param focus 关注点
     * @return 是否添加成功
     */
    public long addStatisticFocus(Focus focus){
        return focusService.addFocus(focus);
    }

    /**
     * 在统计信息中更新关注点
     * @param focus 关注点
     * @return 是否更新成功
     */
    public boolean updateStatisticFocus(Focus focus){
        return focusService.updateFocus(focus);
    }

    /**
     * 当前issue是否已经被信息提取过
     * @param issueId issue id
     * @param csvId csv id，和issueId到一起作为issue的唯一标识
     * @return 是否被提取过
     */
    public boolean isIssueExtracted(long issueId, long csvId){
        return focusService.isIssueExtracted(issueId,csvId);
    }

    /**
     * 将当前一个issue标记为已被提取过
     * @param issueId issue id
     * @param csvId csv id， 和issueId一起作为issue的唯一标识
     * @return 是否标记成功
     */
    public boolean markIssueExtracted(long issueId, long csvId){
        return focusService.markIssueExtracted(issueId,csvId);
    }

    /**
     * 根据issue的Id 和 csv Id 获得已经标记过的issue的关键词
     * @param issueId issue id
     * @param csvId csv id
     * @return issue的关键词
     */
    public List<Keyword> getMarkedIssueKeywords(long issueId, long csvId){
        List<Keyword> keywords = focusService.getMarkedIssueKeywords(issueId,csvId);
        int voteCount;
        for(Keyword keyword : keywords){
            voteCount = 0;
            List<Integer> votes = voteService.getKeywordVote(csvId,issueId,keyword.getId());
            for(Integer i : votes){
                voteCount += i;
            }
            keyword.setVote(voteCount);
        }
        return keywords;
    }

    /**
     * 获得已经被信息提取过的issue的关注点
     * @param issueId
     * @param csvId
     * @return
     */
    public List<Focus> getMarkedIssueFocus(long issueId, long csvId){
        List<Focus> focusList = focusService.getMarkedIssueFocus(issueId, csvId);
        int voteCount;
        for(Focus focus : focusList){
            voteCount = 0 ;
            List<Integer> votes = voteService.getFocusVote(csvId,issueId,focus.getId());
            for(Integer i : votes){
                voteCount += i;
            }
            focus.setVote(voteCount);
        }
        return focusList;
    }

    /**
     * 设置issue的关键词和关注点
     * @param issueId issue id
     * @param csvId csv id
     * @param focusList 关注点列表
     * @param keywords 关键词列表
     * @return 是否设置成功
     */
    public boolean setIssueKeywordsAndFocus(long issueId, long csvId,List<Focus> focusList,  List<Keyword> keywords){
        boolean res = focusService.setIssueKeywordsAndFocus(issueId,csvId,focusList,keywords,0l);
        boolean res1 = markIssueExtracted(issueId,csvId);
        if(res && res1){
            return true;
        }
        return false;
    }


    /**
     * 对于某个issue，获得某用户投过票的关注点
     * @param issueId issue id
     * @param csvId csv id
     * @param userId 用户id
     * @return 用户在这个issue投过票的关注点
     */
    public List<Focus> getVotedIssueFocus(long issueId, long csvId, long userId){
        List<Long> votedFocus = voteService.getFocusByUserId(csvId,issueId,userId);
        List<Focus> focusList = new ArrayList<>();
        for(Long l : votedFocus){
            focusList.add(new Focus(l));
        }
        return focusList;
    }

    /**
     * 对于某个issue，获得某个用户投过票的关键词
     * @param issueId issue id
     * @param csvId csv id
     * @param userId 用户id
     * @return 用户在这个issue投过票的关键词
     */
    public List<Keyword> getVotedIssueKeywords(long issueId, long csvId, long userId){
        List<Long> votedKeywords = voteService.getKeywordByUserId(csvId,issueId,userId);
        List<Keyword> keywords = new ArrayList<>();
        for(Long l : votedKeywords){
            keywords.add(new Keyword(l));
        }
        return keywords;
    }

    /**
     * 对某个issue的某个关注点投票
     * @param issueId
     * @param csvId
     * @param userId
     * @param focusId
     * @return
     */
    public boolean voteIssueFocus(long issueId, long csvId, long userId, long focusId){
        return voteService.addFocusVote(csvId,issueId,focusId,userId,1);
    }

    /**
     * 对某个issue的某个关键词投票
     * @param issueId
     * @param csvId
     * @param userId
     * @param keywordId
     * @return
     */
    public boolean voteIssueKeyword(long issueId, long csvId, long userId, long keywordId){
        return voteService.addKeywordVote(csvId,issueId,keywordId,userId,1);
    }




}
