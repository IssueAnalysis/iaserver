package com.issue.iaserver.extractor.dao;

import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.service.FocusService;
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

    public DaoController(FocusService focusService) {
        this.focusService = focusService;
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
        // TODO 连接数据库
        return false;
    }

    /**
     * 在统计信息中添加关注点
     * @param focus 关注点
     * @return 是否添加成功
     */
    public boolean addStatisticFocus(Focus focus){
        // TODO 连接数据库
        return false;
    }

    /**
     * 在统计信息中更新关注点
     * @param focus 关注点
     * @return 是否更新成功
     */
    public boolean updateStatisticFocus(Focus focus){
        // TODO 连接数据库
        return false;
    }

    /**
     * 当前issue是否已经被信息提取过
     * @param issueId issue id
     * @param csvId csv id，和issueId到一起作为issue的唯一标识
     * @return 是否被提取过
     */
    public boolean isIssueExtracted(long issueId, long csvId){
        // TODO 连接数据层
        return true;
    }

    /**
     * 将当前一个issue标记为已被提取过
     * @param issueId issue id
     * @param csvId csv id， 和issueId一起作为issue的唯一标识
     * @return 是否标记成功
     */
    public boolean markIssueExtracted(long issueId, long csvId){
        // TODO 连接数据层
        return true;
    }

    /**
     * 根据issue的Id 和 csv Id 获得已经标记过的issue的关键词
     * @param issueId issue id
     * @param csvId csv id
     * @return issue的关键词
     */
    public List<Keyword> getMarkedIssueKeywords(long issueId, long csvId){
        // TODO 连接数据库
        return null;
    }

    /**
     * 获得已经被信息提取过的issue的关注点
     * @param issueId
     * @param csvId
     * @return
     */
    public List<Focus> getMarkedIssueFocus(long issueId, long csvId){
        // TODO 连接数据库
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
    public boolean setIssueKeywordsAndFocus(long issueId, long csvId,List<Focus> focusList,  List<Keyword> keywords){
        // TODO 连接数据库
        return false;
    }


}
