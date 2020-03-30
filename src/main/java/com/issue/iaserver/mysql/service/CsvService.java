package com.issue.iaserver.mysql.service;

import com.issue.iaserver.mysql.entity.CsvDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:32
 */

public interface CsvService {
    /**
     * 保存黑名单数据
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
