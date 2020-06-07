package com.issue.iaserver.extractor.server;

import com.issue.iaserver.extractor.dao.DaoController;
import com.issue.iaserver.extractor.focus.Focus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songjinze
 * @date 2020/5/16 3:49 下午
 */
@Service("focusInfoService")
public class FocusInfoController implements FocusInfoService{


    private DaoController daoController;

    @Autowired
    public FocusInfoController(DaoController daoController) {
        this.daoController = daoController;
    }

    @Override
    public List<Focus> getAllFocus() {
        List<Focus> focusList = daoController.getAllFocus();
        if(focusList.isEmpty()){
            Focus testFocus = new Focus(
                    "No data found in database",
                    new ArrayList<>(),
                    "Something is wrong!"
            );
            focusList.add(testFocus);
        }
        return focusList;
    }

    @Override
    public long addFocus(Focus focus) {
        if(daoController.isStatisticHasFocus(focus)){
            return -1;
        }
        return daoController.addStatisticFocus(focus);
    }

    @Override
    public ExtractMessage updateFocus(Focus focus) {
        if(!daoController.isStatisticHasFocus(focus)){
            return ExtractMessage.NO_DATA;
        }
        boolean res = daoController.updateStatisticFocus(focus);
        if(res){
            return ExtractMessage.SUCCESS;
        }else{
            return ExtractMessage.DATA_ERROR;
        }
    }
}
