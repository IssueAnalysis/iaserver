package com.issue.iaserver.nlp.model;

public enum ModelType {
    SENTENCE_MODEL("sentence"),
    TOKEN_MODEL("token");

    private String type;

    ModelType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
