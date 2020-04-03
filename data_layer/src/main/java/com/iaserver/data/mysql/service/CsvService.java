package com.iaserver.data.mysql.service;

import com.iaserver.data.mysql.entity.CsvDO;

import java.util.List;
/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:32
 */

public interface CsvService {
    /**
     * 保存数据
     * @param csvDO
     * @return
     */
    public CsvDO insert(CsvDO csvDO);

    /**
     *
     * @return
     */
    public List<CsvDO> queryAll();
}
