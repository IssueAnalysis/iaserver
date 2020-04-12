package com.issue.iaserver.data.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮箱配置属性
 *
 * @author 钟镇鸿
 * @since 2020/3/20 23:17
 */
@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfigProperty {

    /**
     * 发件邮箱账户
     */
    private String username;

    public String getUsername() {
        return username;
    }

    public MailConfigProperty setUsername(String username) {
        this.username = username;
        return this;
    }
}
