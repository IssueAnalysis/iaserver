package com.issue.iaserver.nlp.wnl;

import com.issue.iaserver.nlp.pojos.PosTagDic;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerType;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.relationship.Relationship;
import net.sf.extjwnl.data.relationship.RelationshipFinder;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// wordnet使用进行同义词辨析等
@Service
public class WordSimilarity implements WnlService{
    private Logger logger = LoggerFactory.getLogger(WordSimilarity.class);

    private PosTagDic posTagDic;
    private Dictionary dictionary;
    @Autowired
    public WordSimilarity(PosTagDic posTagDic,Dictionary dictionary){
        this.posTagDic = posTagDic;
        this.dictionary = dictionary;
    }

    /**
     * 获得两个词之间的相似度，基于WordNet词典，使用路径算法
     * @param inputWord 词1
     * @param inputTag  词1的词性，方法只判断名词和动词
     * @param targetWord 词2
     * @param targetTag 词2的词性，方法只判断名词和动词
     * @return 相似度，取值在(0,1]。
     *         若词1或词2的词性不满足条件，则返回0。
     */
    @Override
    public double getSimilarityByPath(String inputWord, String inputTag, String targetWord, String targetTag){
        POS inputPos;
        POS targetPOS;
        if(posTagDic.isVerb(inputTag)){
            inputPos = POS.VERB;
        }else if(posTagDic.isNoun(inputTag)){
            inputPos = POS.NOUN;
        }else{
            logger.info("Input word :{}, tag is :{} is not a verb or noun.",inputWord, inputTag);
            return 0;
        }
        if(posTagDic.isVerb(targetTag)){
            targetPOS = POS.VERB;
        }else if(posTagDic.isNoun(targetTag)){
            targetPOS = POS.NOUN;
        }else{
            logger.info("Target word :{}, tag is :{} is not a verb of noun.",targetWord, targetTag);
            return 0;
        }
        try {
            IndexWord inputIndexWord = dictionary.getIndexWord(inputPos,inputWord);
            IndexWord targetIndexWord = dictionary.getIndexWord(targetPOS,targetWord);
            if (logger.isDebugEnabled()) {
                logger.debug(inputIndexWord.getSenses().get(0).toString());
                logger.debug(inputIndexWord.getSenses().get(0).toString());
            }
            RelationshipList relationships = RelationshipFinder.findRelationships(inputIndexWord.getSenses().get(0),
                    targetIndexWord.getSenses().get(0),PointerType.HYPERNYM);
            return 1.0 / (relationships.getShallowest().getDepth() + 1);
        } catch (JWNLException | CloneNotSupportedException e) {
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public double getSimilarityByLin(String inputWord, String inputTag, String targetWord, String targetTag){
        // TODO 添加同义词辨析方法
        return 0;
    }

    /**
     * 计算两个字符串间的编辑距离
     * @param input
     * @param target
     * @return
     */
    @Override
    public int getEditDistance(String input, String target){
        int m = input.length();
        int n = target.length();
        if(m == 0) return n;
        if(n == 0) return m;
        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++){
            matrix[i][0] = i;
        }
        for(int j = 0; j < n; j++){
            matrix[0][j] = j;
        }
        for(int i = 1; i < m; i++)
            for(int j = 1; j < n; j++){
                int t = input.charAt(i) == target.charAt(j) ? 0 : 1;
                matrix[i][j] = Math.min(matrix[i - 1][j - 1] + t,
                        Math.min(matrix[i - 1][j] + 1,matrix[i][j - 1] + 1));
            }
        return matrix[m - 1][n - 1];
    }









    public static void main(String[] args) throws JWNLException, CloneNotSupportedException {
        Dictionary dictionary = Dictionary.getDefaultResourceInstance();
        IndexWord indexWord = dictionary.getIndexWord(POS.ADJECTIVE,"big");

        IndexWord indexWord1 = dictionary.getIndexWord(POS.NOUN,"ground");
        IndexWord indexWord2 = dictionary.getIndexWord(POS.ADJECTIVE,"large");
        List<Synset> synsetList = indexWord.getSenses();
        RelationshipList relationships = RelationshipFinder.findRelationships(indexWord.getSenses().get(0),indexWord1.getSenses().get(0), PointerType.HYPERNYM);
        RelationshipList relationships1 = RelationshipFinder.findRelationships(indexWord.getSenses().get(0),indexWord2.getSenses().get(0),PointerType.HYPERNYM);
        System.out.println(relationships1.size());
        for(Relationship relationship : relationships1){
            System.out.println(relationship.getDepth());
        }
        System.out.println(relationships1.getDeepest().getDepth());
        System.out.println(relationships1.getShallowest().getDepth());
        for(Synset synset : synsetList){

            System.out.println(synset.getWords());
            System.out.println(synset.getPointers());
        }

    }
}
