package com.issue.iaserver.bean;

import java.io.Serializable;

/**
 * @function 发送邮箱-封装接收者信息
 */
public class MailBean implements Serializable {
    /**邮件接收人*/
    private String recipient;
    /**邮件主题*/
    private String subject;
    /**邮件内容*/
    private String content;
}
