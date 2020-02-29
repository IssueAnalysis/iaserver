package com.issue.iaserver.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories("com.example.springdatajdbcdemo") //这里的扫描目录要写好了
public class JdbcConfig {

}
