package com.issue.iaserver.webserver.controller;

import com.issue.iaserver.extractor.focus.Focus;
import com.issue.iaserver.extractor.keyword.Keyword;
import com.issue.iaserver.extractor.server.FocusInfoService;
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
    public void addFocus(@RequestBody StatisticFocus statisticFocus){
        List<StatisticKeyword> statisticKeywords = statisticFocus.getKeywordList();
        long id = statisticFocus.getId();
        String description = statisticFocus.getDescription();
        String type = statisticFocus.getType();
        List<Keyword> keywords = new ArrayList<>();
        for(StatisticKeyword keyword : statisticKeywords){
            keywords.add(new Keyword(keyword.getDescription(),0));
        }
        Focus focus = new Focus(id,description,keywords,type);
        focusInfoService.addFocus(focus);
    }

    @PostMapping("update_focus")
    public void updateFocus(@RequestBody StatisticFocus statisticFocus){
        List<StatisticKeyword> statisticKeywords = statisticFocus.getKeywordList();
        long id = statisticFocus.getId();
        String description = statisticFocus.getDescription();
        String type = statisticFocus.getType();
        List<Keyword> keywords = new ArrayList<>();
        for(StatisticKeyword keyword : statisticKeywords){
            keywords.add(new Keyword(keyword.getDescription(),0));
        }
        Focus focus = new Focus(id,description,keywords,type);
        focusInfoService.updateFocus(focus);
    }
}
