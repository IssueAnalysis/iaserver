package com.issue.iaserver.nlp.model;

public enum ModelType {
    SENTENCE_MODEL("sentence"),
    POS_MODEL("pos"),
    TOKEN_MODEL("token"),
    CHUNKER_MODEL("chunker");

    private String type;

    ModelType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
