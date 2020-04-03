package com.iaserver.data.mysql.util;

import com.iaserver.data.mysql.entity.CsvDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:36
 */
public class CsvDOItemProcessor implements ItemProcessor<CsvDO, CsvDO> {

    private static final Logger logger = LoggerFactory.getLogger(CsvDOItemProcessor.class);

    public CsvDO process(CsvDO csvDO) throws Exception {
        logger.info("============批量自动导入数据process===============");
        //csvDO.setDescription("批量自动导入数据");

        //csvDO.setGmtCreate(new Timestamp(new Date().getTime()));
        return csvDO;
    }

}
