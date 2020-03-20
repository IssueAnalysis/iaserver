package com.issue.iaserver.mysql.util;

import com.issue.iaserver.mysql.entity.CsvDO;
import org.springframework.batch.item.file.transform.FieldSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:36
 */
public class CsvFieldSetMapper implements FieldSetMapper<CsvDO> {
    private static final Logger logger = LoggerFactory.getLogger(CsvFieldSetMapper.class);

    @Override
    public CsvDO mapFieldSet(FieldSet fieldSet) throws BindException {

        logger.info("============CsvFieldSetMapper转换POJO=============");
        CsvDO csvDO = new CsvDO();
        /*csvDO.setUuid(fieldSet.readString("uuid"));
        csvDO.setListNameUuid(fieldSet.readString("listNameUuid"));
        csvDO.setAppName(fieldSet.readString("appName"));
        csvDO.setPartnerCode(fieldSet.readString("partnerCode"));*/
        return csvDO;
    }
}
