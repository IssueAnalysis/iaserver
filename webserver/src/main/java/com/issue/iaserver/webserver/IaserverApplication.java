package com.issue.iaserver.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**主入口*/
@SpringBootApplication
@ComponentScan({"com.iaserver.data",
        "com.issue.iaserver.webserver",
        "com.issue.iaserver.nlp"})
@EnableJpaRepositories("com.iaserver.data.mysql.dao")
@EntityScan("com.iaserver.data.mysql.entity")
public class IaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaserverApplication.class, args);
    }

}
