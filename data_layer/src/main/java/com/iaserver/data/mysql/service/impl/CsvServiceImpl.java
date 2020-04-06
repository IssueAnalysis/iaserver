package com.iaserver.data.mysql.service.impl;

import com.iaserver.data.mysql.dao.CsvDao;
import com.iaserver.data.mysql.service.CsvService;
import com.iaserver.data.mysql.entity.CsvDO;
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
@Service
public class CsvServiceImpl implements CsvService {
    @Autowired
    private CsvDao csvDao;

    /**
     * 保存数据
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
