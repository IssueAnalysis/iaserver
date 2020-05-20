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
    public ExtractMessage addFocus(Focus focus) {

        return null;
    }

    @Override
    public ExtractMessage updateFocus(Focus focus) {
        return null;
    }
}
