package com.issue.iaserver.extractor.nlpUtil;

import com.issue.iaserver.extractor.model.ModelNotFoundException;
import com.issue.iaserver.extractor.model.Models;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.util.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Detector {

    private final Models models;

    private final Logger logger = LoggerFactory.getLogger(Detector.class);

    @Autowired
    public Detector(Models models) {
        this.models = models;
        try {
            this.sentenceDetectorME = new SentenceDetectorME(models.loadSentenceModel());
            this.tokenizerME = new TokenizerME(models.loadTokenizerModel());
            this.posTaggerME = new POSTaggerME(models.loadPosModel());
            this.chunkerME = new ChunkerME(models.loadChunkerModel());
        } catch (ModelNotFoundException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private SentenceDetectorME sentenceDetectorME;
    private TokenizerME tokenizerME;
    private POSTaggerME posTaggerME;
    private ChunkerME chunkerME;


    public String[] detectSentences(String text) {
        return sentenceDetectorME.sentDetect(text);
    }

    public String[] detectTokens(String text) {
        return tokenizerME.tokenize(text);
    }

    public String[] detectPos(String text) {
        String[] tokens = detectTokens(text);
        return detectPos(tokens);
    }

    public String[] detectPos(String[] tokens){
        return posTaggerME.tag(tokens);
    }

    public String[] detectChunker(String text) {
        return chunkerME.chunk(detectTokens(text), detectPos(text));
    }

    public Span[] getChunkSpan(String text) {
        return chunkerME.chunkAsSpans(detectTokens(text), detectPos(text));
    }

}
