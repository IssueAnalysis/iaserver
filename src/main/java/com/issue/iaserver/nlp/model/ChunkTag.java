package com.issue.iaserver.nlp.model;

public enum ChunkTag {
    NOUN_PHASE("NP","名词组"),
    VERB_PHASE("VP","动词组"),
    ADJECTIVE_PHASE("ADJP","形容词组"),
    ADVERB_PHASE("ADVP","副词组"),
    CONJUNCTION_PHASE("CONJP","连接词组"),
    PREPOSITION_PHASE("PP","介词");

    private String type;
    private String typeInCn;

    ChunkTag(String type, String typeInCN){
        this.type = type;
        this.typeInCn = typeInCN;
    }

    public String getType() {
        return type;
    }

    public String getTypeInCn() {
        return typeInCn;
    }
}
