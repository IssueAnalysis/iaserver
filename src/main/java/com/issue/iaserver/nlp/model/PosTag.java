package com.issue.iaserver.nlp.model;

public enum PosTag {
    CC("CC", "Coordinating conjunction", "等价连接词"),
    CD("CD", "Cardinal number", "基本数字"),
    DT("DT", "Determiner", "限定词"),
    EX("EX", "Existential there", ""),
    FW("FW", "Foreign word", "外语词"),
    IN("IN", "Preposition or subordinating conjunction", ""),
    JJ("JJ", "Adjective", ""),
    JJR("JJR", "Adjective,comparative", ""),
    JJS("JJS", "Adjective,superlative", ""),
    NN("NN", "Noun,singular or mass", ""),
    NNS("NNS","Noun,plural",""),
    NNP("NNP","Proper noun, singular",""),
    NNPS("NNPS", "Proper noun, plural", ""),
    PDT("PDT", "Predeterminer", ""),
    NP("NP", "Noun Phrase", ""),
    PP("PP", "Prepositional Phrase", ""),
    VP("VP", "Verb Phrase", ""),
    PRP("PRP", "Personal pronoun", ""),
    RB("RB", "Adverb", ""),
    RBR("RBR", "Adverb, comparative", ""),
    RBS("RBS", "Adverb, superlative", ""),
    RP("RP", "Particle", ""),
    SYM("SYM", "Symbol", ""),
    TO("TO","to",""),
    UH("UH", "Interjection", ""),
    VB("VB", "Verb,base form", "动词原型"),
    VBD("VBD", "Verb,past tense", "动词过去式"),
    VBG("VBG", "Verb,gerund or present participle", ""),
    VBN("VBN", "Verb,past participle", ""),
    VBP("VBP", "Verb,non-3rd person singular present", ""),
    VBZ("VBZ", "Verb,3rd person singular present", ""),
    WDT("WDT", "Wh-determiner", ""),
    WP("WP", "Wh-pronoun", ""),
    WRB("WRB", "Wh-adverb", "");


    private String name;
    private String description;
    private String descriptionInCn;

    PosTag(String name, String description, String descriptionInCn) {
        this.name = name;
        this.description = description;
        this.descriptionInCn = descriptionInCn;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionInCn() {
        return descriptionInCn;
    }
}
