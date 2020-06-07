package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.service.FocusService;
import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import com.issue.iaserver.extractor.server.FocusInfoService;
import com.issue.iaserver.webserver.model.FocusIssueMap;
import com.issue.iaserver.webserver.model.StatisticFocus;
import com.issue.iaserver.webserver.model.StatisticKeyword;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/18 8:41 下午
 */

@RestController
@RequestMapping("/api/statistic/")
public class StatisticController {

    @Resource(name = "focusInfoService")
    private FocusInfoService focusInfoService;

    private FocusService focusService;

    @GetMapping("focus_info")
    public List<StatisticFocus> getAllFocusInfo(){
        List<Focus> foci = focusInfoService.getAllFocus();
        List<StatisticFocus> res = new ArrayList<>(foci.size());
        for(Focus focus : foci){
            res.add(new StatisticFocus(focus));
        }
        return res;
    }

    @PostMapping("add_focus")
    public long addFocus(@RequestBody StatisticFocus statisticFocus){
        List<StatisticKeyword> statisticKeywords = statisticFocus.getKeywordList();
        long id = statisticFocus.getId();
        String description = statisticFocus.getDescription();
        String type = statisticFocus.getType();
        List<Keyword> keywords = new ArrayList<>();
        for(StatisticKeyword keyword : statisticKeywords){
            keywords.add(new Keyword(keyword.getDescription(),0));
        }
        Focus focus = new Focus(id,description,keywords,type);
        return focusInfoService.addFocus(focus);
    }

    @PostMapping("update_focus")
    public String updateFocus(@RequestBody StatisticFocus statisticFocus){
        List<StatisticKeyword> statisticKeywords = statisticFocus.getKeywordList();
        long id = statisticFocus.getId();
        String description = statisticFocus.getDescription();
        String type = statisticFocus.getType();
        List<Keyword> keywords = new ArrayList<>();
        for(StatisticKeyword keyword : statisticKeywords){
            keywords.add(new Keyword(keyword.getDescription(),0));
        }
        Focus focus = new Focus(id,description,keywords,type);
        return focusInfoService.updateFocus(focus).getMessage();
    }

    @GetMapping("focus_issue")
    public FocusIssueMap getFocusIssues(){
        List<FocusDO> focusDOS = focusService.getAllIssueFocus();
        List<Focus> focusList = focusInfoService.getAllFocus();
        FocusIssueMap focusIssueMap = new FocusIssueMap();
        List<String> focuses = new ArrayList<>();
        List<Integer> issueCount = new ArrayList<>();
        int count = 0;
        for(Focus focus : focusList){
            focuses.add(focus.getFocusDescription());
            count = 0;
            for(FocusDO focusDO : focusDOS){
                if(focusDO.getFocusDescription().equals(focus.getFocusDescription())
                && focusDO.getFocusType().equals(focus.getFocusType())){
                    count++;
                }
            }
            issueCount.add(count);
        }
        focusIssueMap.setCounts(issueCount);
        focusIssueMap.setFocuses(focuses);
        return focusIssueMap;
    }
}
