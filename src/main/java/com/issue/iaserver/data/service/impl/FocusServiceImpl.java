package com.issue.iaserver.data.service.impl;

import com.issue.iaserver.data.mysql.dao.FocusDao;
import com.issue.iaserver.data.mysql.dao.UserDao;
import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<FocusDO> getAllFocus() {
        return focusDao.findAll();
    }
}
