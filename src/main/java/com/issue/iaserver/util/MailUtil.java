package com.issue.iaserver.util;

import org.springframework.mail.SimpleMailMessage;

public class MailUtil {

    public void sendSimpleEmail() {
        // 构造Email消息
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("159****2662@163.com");
        message.setTo("****@qianxx.com");
        message.setSubject("邮件主题");
        message.setText("邮件内容");
        //javaMailSender.send(message);
    }
}
