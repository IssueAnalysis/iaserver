package com.issue.iaserver.extractor.focus.wnl;

import com.issue.iaserver.extractor.pojos.PosTagDic;
import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.lexical_db.data.Concept;
import edu.cmu.lti.ws4j.Relatedness;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.Lin;
import edu.cmu.lti.ws4j.impl.Path;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimilarityCalculator {
    private ILexicalDatabase db;

    private RelatednessCalculator linCalculator;

    private PosTagDic posDictionary;
    @Autowired
    public SimilarityCalculator(PosTagDic posDictionary){
        db = new NictWordNet();
        linCalculator = new Lin(db);
        this.posDictionary = posDictionary;

    }

    public double computeByLin(String word1,String word1Pos, String word2,String word2Pos) {
        WS4JConfiguration.getInstance().setMFS(true);
        POS pos1 = null;
        POS pos2 = null;
        if(posDictionary.isNoun(word1Pos)) pos1 = POS.n;
        else if(posDictionary.isVerb(word1Pos)) pos1 = POS.v;
        if(posDictionary.isNoun(word2Pos)) pos2 = POS.n;
        else if(posDictionary.isVerb(word2Pos)) pos2 = POS.v;
        if(pos1 == null || pos2 == null){
            return 0;
        }
        List<Concept> concepts = (List<Concept>)db.getAllConcepts(word1,pos1.toString());
        List<Concept> concepts1 = (List<Concept>)db.getAllConcepts(word2,pos2.toString());
        double maxScore = -1D;
        for(Concept concept : concepts){
            for(Concept concept1 : concepts1){
                Relatedness relatedness = linCalculator.calcRelatednessOfSynset(concept1, concept);
                double score = relatedness.getScore();
                if(score > maxScore){
                    maxScore = score;
                }
            }
        }
        if(maxScore == -1D){
            return 0.0;
        }
        return maxScore;
    }

    public double computeByPath(String word1, String word2){
        WS4JConfiguration.getInstance().setMFS(true);
        return new Path(db).calcRelatednessOfWords(word1,word2);
    }
}
