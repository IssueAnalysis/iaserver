package com.issue.iaserver.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**主入口*/
@Configuration
@PropertySource(value = "classpath:application_data.properties")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class },
        scanBasePackages = {"com.iaserver.data",
                "com.issue.iaserver.webserver",
                "com.issue.iaserver.nlp"})
public class IaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaserverApplication.class, args);
    }

}
