package com.issue.iaserver.extractor.focus;

import com.issue.iaserver.extractor.dao.DaoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FocusController {

    @Resource(type = DaoController.class)
    private DaoController daoController;

    public List<Focus> getAllFocus(){
        return daoController.getAllFocus();
    }

}
