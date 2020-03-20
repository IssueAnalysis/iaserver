package com.issue.iaserver.mysql.listener;

import com.issue.iaserver.mysql.entity.CsvDO;
import com.issue.iaserver.mysql.service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 监听类
 *
 * @author 钟镇鸿
 * @since 2020/3/15 0:31
 */
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private CsvService csvService;

    @Override
    public void afterJob(JobExecution jobExecution) {

        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("=============JOB FINISHED! Time to verify the results===============");
            List<CsvDO> results = csvService.queryAll();
            for (CsvDO csvDO : results) {
                logger.info("Found <" + csvDO.getId() + "> in the database.");
            }
        }
    }
}

