package webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**主入口*/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@ComponentScan({"data", "webserver", "com.issue.iaserver.nlp"})
@EnableJpaRepositories("data.mysql.dao")
@EntityScan("data.mysql.entity")
@EnableAutoConfiguration
public class IaserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(IaserverApplication.class, args);
    }

}
