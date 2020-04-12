package com.issue.iaserver.nlp.pojos;

import com.issue.iaserver.nlp.model.PosTags;

public class PosTagDic {
    private static final String[][] tagDic = {
            {"noun phrase", PosTags.NOUN_PHASE.getType(), PosTags.NOUN_PHASE.getTypeInCn()},
            {"verb phrase", PosTags.VERB_PHASE.getType(), PosTags.VERB_PHASE.getTypeInCn()},
            {"adjective phrase", PosTags.ADJECTIVE_PHASE.getType(), PosTags.ADJECTIVE_PHASE.getTypeInCn()},
            {"adverb phrase", PosTags.ADVERB_PHASE.getType(), PosTags.ADVERB_PHASE.getTypeInCn()},
            {"conjunction phrase", PosTags.CONJUNCTION_PHASE.getType(), PosTags.CONJUNCTION_PHASE.getTypeInCn()},
            {"preposition", PosTags.PREPOSITION_PHASE.getType(), PosTags.PREPOSITION_PHASE.getTypeInCn()}
    };

    public static String enToCn(String tagInEn){
        for(String[] tag : tagDic){
            if(tagInEn.equals(tag[1])){
                return tag[2];
            }
        }
        return "";
    }

    private PosTagDic(){}
}
