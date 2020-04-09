package com.issue.iaserver.nlp.pojos;

public class PosTags {
    private static final String[][] tagDic = {
            {"noun phrase", "NP", "名词组"},
            {"verb phrase", "VP", "动词组"},
            {"adjective phrase", "ADJP", "形容词组"},
            {"adverb phrase", "ADVP", "副词组"},
            {"conjunction phrase", "CONJP", "连接词组"},
            {"preposition", "PP", "介词"}
    };

    public static String enToCn(String tagInEn){
        for(String[] tag : tagDic){
            if(tagInEn.equals(tag[1])){
                return tag[2];
            }
        }
        return "";
    }

    private PosTags(){}
}
