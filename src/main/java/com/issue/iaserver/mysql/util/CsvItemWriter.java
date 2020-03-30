package com.issue.iaserver.mysql.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.issue.iaserver.mysql.entity.CsvDO;
import com.issue.iaserver.mysql.service.CsvService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:36
 */
public class CsvItemWriter  implements ItemWriter<CsvDO> {

    private static final Logger logger = LoggerFactory.getLogger(CsvItemWriter.class);

    @Autowired
    private CsvService csvService;

    @Override
    public void write(List<? extends CsvDO> csvList) throws Exception {
        logger.info("=========入库========");
        for(CsvDO csvDO : csvList){
            //jdbcTemplate.update( INSERT_BLACKLIST, blackListDO.getId(),blackListDO.getUuid(), blackListDO.getListNameUuid(),blackListDO.getAppName(),blackListDO.getPartnerCode());
            csvService.insert(csvDO);
        }
    }
}
