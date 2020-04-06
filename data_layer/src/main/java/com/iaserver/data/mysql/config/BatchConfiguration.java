package com.iaserver.data.mysql.config;

import javax.sql.DataSource;

import com.iaserver.data.mysql.entity.CsvDO;
import com.iaserver.data.mysql.util.CsvDOItemProcessor;
import com.iaserver.data.mysql.util.CsvFieldSetMapper;
import com.iaserver.data.mysql.util.CsvItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import java.net.MalformedURLException;

/**
 * 读取外部csv文件配置类
 *
 * @author 钟镇鸿
 * @since 2020/3/19 0:23
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);

    private String fileURL = "https://iaserver.oss-cn-hangzhou.aliyuncs.com/test.csv";
    /**
     * 读取外部文件方法
     * @return
     */
    @Bean
    public ItemReader<CsvDO> reader() {
        logger.info("=========reader========");
        FlatFileItemReader<CsvDO> reader = new FlatFileItemReader<CsvDO>();
        try {
            reader.setResource(new UrlResource(fileURL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        reader.setLineMapper(lineMapper());
        return reader;
    }

    /**
     * 读取文本行映射POJO
     * @return
     */
    @Bean
    public LineMapper<CsvDO> lineMapper() {
        logger.info("=========lineMapper========");
        DefaultLineMapper<CsvDO> lineMapper = new DefaultLineMapper<CsvDO>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] { "uuid","listNameUuid","appName","partnerCode"});

        BeanWrapperFieldSetMapper<CsvDO> fieldSetMapper = new BeanWrapperFieldSetMapper<CsvDO>();
        fieldSetMapper.setTargetType(CsvDO.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(new CsvFieldSetMapper());
        return lineMapper;
    }

    /**
     * 处理过程
     * @return
     */
    @Bean
    public ItemProcessor<CsvDO, CsvDO> processor() {
        logger.info("=========processor========");
        return new CsvDOItemProcessor();
    }

    /**
     * 写出内容
     * @return
     */
    @Bean
    public ItemWriter<CsvDO> writer() {
        logger.info("=========writer========");
        return new CsvItemWriter();
    }

    /**
     * 构建job
     * @param jobs
     * @param s1
     * @param listener
     * @return
     */
    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener, JobRepository jobRepository) {
        logger.info("=========job========");
        return jobs.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .repository(jobRepository)
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    /**
     * 声明step
     * @param stepBuilderFactory
     * @param reader
     * @param writer
     * @param processor
     * @return
     */
    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<CsvDO> reader,
                      ItemWriter<CsvDO> writer, ItemProcessor<CsvDO, CsvDO> processor, PlatformTransactionManager transactionManager) {
        logger.info("=========step========");
        return stepBuilderFactory.get("step1")
                .<CsvDO, CsvDO> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skipLimit(100)
                .skip(Exception.class)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
