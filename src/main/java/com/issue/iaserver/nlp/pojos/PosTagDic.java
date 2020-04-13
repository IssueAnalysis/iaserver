package com.issue.iaserver.nlp.pojos;

import com.issue.iaserver.nlp.model.PosTag;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class PosTagDic {
    private final Map<String, PosTag> posTagsMap;

    private final Map<String, PosTag> nounTagsMap;

    private final Map<String, PosTag> verbTagsMap;

    public PosTagDic(){
        posTagsMap = new HashMap<>();
        PosTag[] posTags = PosTag.values();
        for(PosTag posTag : posTags){
            posTagsMap.put(posTag.getName(),posTag);
        }
        nounTagsMap = new HashMap<>();
        nounTagsMap.put(PosTag.NN.getName(),PosTag.NN);
        nounTagsMap.put(PosTag.NNS.getName(),PosTag.NNS);
        nounTagsMap.put(PosTag.NNP.getName(),PosTag.NNP);
        nounTagsMap.put(PosTag.NNPS.getName(), PosTag.NNPS);
        nounTagsMap.put(PosTag.NP.getName(),PosTag.NP);
        verbTagsMap = new HashMap<>();
        verbTagsMap.put(PosTag.VP.getName(),PosTag.VP);
        verbTagsMap.put(PosTag.VB.getName(),PosTag.VB);
        verbTagsMap.put(PosTag.VBD.getName(),PosTag.VBD);
        verbTagsMap.put(PosTag.VBG.getName(), PosTag.VBG);
        verbTagsMap.put(PosTag.VBN.getName(), PosTag.VBN);
        verbTagsMap.put(PosTag.VBP.getName(), PosTag.VBP);
        verbTagsMap.put(PosTag.VBZ.getName(), PosTag.VBZ);
    }

    public PosTag getPosTag(String tagName){
        return posTagsMap.get(tagName);
    }

    public boolean isNoun(String tagName){
        return nounTagsMap.get(tagName) != null;
    }

    public boolean isVerb(String tagName){
        return verbTagsMap.get(tagName) != null;
    }
}
