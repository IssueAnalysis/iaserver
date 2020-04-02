package com.issue.iaserver.nlp.model;

public class ModelNotFoundException extends Exception {
    private String message;

    public ModelNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
