package com.issue.iaserver.extractor.server;

/**
 * @author songjinze
 * @date 2020/5/16 3:47 下午
 */
public enum  ExtractMessage {

    SUCCESS("success"),
    DATA_ERROR("data error"),
    NO_DATA("no data"),
    DUPLICATED("data duplicated");

    private final String message;

    ExtractMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
