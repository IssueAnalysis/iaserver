package com.issue.iaserver.extractor.pojos;

import com.issue.iaserver.extractor.model.ChunkTag;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ChunkTagDic {

    private static final String[][] tagDic = {
            {"noun phrase", ChunkTag.NOUN_PHASE.getType(), ChunkTag.NOUN_PHASE.getTypeInCn()},
            {"verb phrase", ChunkTag.VERB_PHASE.getType(), ChunkTag.VERB_PHASE.getTypeInCn()},
            {"adjective phrase", ChunkTag.ADJECTIVE_PHASE.getType(), ChunkTag.ADJECTIVE_PHASE.getTypeInCn()},
            {"adverb phrase", ChunkTag.ADVERB_PHASE.getType(), ChunkTag.ADVERB_PHASE.getTypeInCn()},
            {"conjunction phrase", ChunkTag.CONJUNCTION_PHASE.getType(), ChunkTag.CONJUNCTION_PHASE.getTypeInCn()},
            {"preposition", ChunkTag.PREPOSITION_PHASE.getType(), ChunkTag.PREPOSITION_PHASE.getTypeInCn()}
    };

    public String enToCn(String tagInEn){
        for(String[] tag : tagDic){
            if(tagInEn.equals(tag[1])){
                return tag[2];
            }
        }
        return "";
    }
}
