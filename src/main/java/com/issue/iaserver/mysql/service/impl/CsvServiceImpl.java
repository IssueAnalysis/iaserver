package com.issue.iaserver.mysql.service.impl;

import com.issue.iaserver.mysql.dao.CsvDao;
import com.issue.iaserver.mysql.service.CsvService;
import com.issue.iaserver.mysql.entity.CsvDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:34
 */
@Service("CsvService")
@Transactional
public class CsvServiceImpl implements CsvService {
    private CsvDao csvDao;

    @Autowired
    public CsvServiceImpl(CsvDao csvDao) {
        this.csvDao = csvDao;
    }

    /**
     * 保存黑名单数据
     * @param csvDO
     * @return
     */
    @Override
    public CsvDO insert(CsvDO csvDO) {
        return this.csvDao.save(csvDO);
    }

    /**
     *
     * @return
     */
    @Override
    public List<CsvDO> queryAll() {
        return (List<CsvDO>) this.csvDao.findAll();
    }
}
