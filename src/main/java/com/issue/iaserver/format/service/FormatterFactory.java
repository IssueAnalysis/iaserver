package com.issue.iaserver.format.service;

import com.issue.iaserver.format.service.impl.FormatterImpl;

public class FormatterFactory {
    public static Formatter getFormatterService(){
        return new FormatterImpl();
    }
}
