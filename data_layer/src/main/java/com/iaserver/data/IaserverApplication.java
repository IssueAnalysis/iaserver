package com.iaserver.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**主入口*/

@SpringBootApplication
public class IaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaserverApplication.class, args);
    }

}
