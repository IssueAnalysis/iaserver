package com.issue.iaserver.nlp.demo;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.model.Models;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Detector {

    private final Models models;

    @Autowired
    public Detector(Models models) {
        this.models = models;
    }

    private SentenceModel sentenceModel;
    private TokenizerModel tokenizerModel;
    private POSModel posModel;
    private ChunkerModel chunkerModel;


    public String[] detectSentences(String text) throws ModelNotFoundException, IOException {
        if(sentenceModel == null){
            sentenceModel = models.loadSentenceModel();
        }
        SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(sentenceModel);
        return sentenceDetectorME.sentDetect(text);
    }

    public String[] detectTokens(String text) throws ModelNotFoundException, IOException {
        if(tokenizerModel == null){
            tokenizerModel = models.loadTokenizerModel();
        }
        TokenizerME tokenizerME = new TokenizerME(tokenizerModel);
        return tokenizerME.tokenize(text);
    }

    public String[] detectPos(String text) throws ModelNotFoundException, IOException {
        if(posModel == null){
            posModel = models.loadPosModel();
        }
        POSTaggerME posTaggerME = new POSTaggerME(posModel);
        String[] tokens = detectTokens(text);
        return posTaggerME.tag(tokens);
    }

    public String[] detectChunker(String text) throws ModelNotFoundException, IOException {
        if(chunkerModel == null){
            chunkerModel = models.loadChunkerModel();
        }
        ChunkerME chunkerME = new ChunkerME(chunkerModel);
        return chunkerME.chunk(detectTokens(text), detectPos(text));
    }

    public Span[] getChunkSpan(String text) throws ModelNotFoundException, IOException {
        if(chunkerModel == null){
            chunkerModel = models.loadChunkerModel();
        }
        ChunkerModel chunkerModel = models.loadChunkerModel();
        ChunkerME chunkerME = new ChunkerME(chunkerModel);
        return chunkerME.chunkAsSpans(detectTokens(text), detectPos(text));
    }

}
