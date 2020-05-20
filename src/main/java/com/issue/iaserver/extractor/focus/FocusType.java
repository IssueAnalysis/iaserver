package com.issue.iaserver.extractor.focus;

public enum  FocusType {
    LOG_TYPE("日志类型"),
    LOG_FOCUS("日志考虑因素");

    String type;

    FocusType(String type) {
        this.type = type;
    }
}
