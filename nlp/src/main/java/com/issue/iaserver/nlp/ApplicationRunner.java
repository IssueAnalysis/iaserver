package com.issue.iaserver.nlp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ApplicationRunner {
    public static void main(String[] args){
        SpringApplication.run(ApplicationRunner.class,args);
    }
}
