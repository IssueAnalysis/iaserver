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
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Detector {

    private Models models;

    public Detector() {
        models = new Models();
    }


    public String[] detectSentences(String text) throws ModelNotFoundException, IOException {
        SentenceModel sentenceModel = models.loadSentenceModel();
        SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(sentenceModel);
        return sentenceDetectorME.sentDetect(text);
    }

    public String[] detectTokens(String text) throws ModelNotFoundException, IOException {
        TokenizerModel tokenizerModel = models.loadTokenizerModel();
        TokenizerME tokenizerME = new TokenizerME(tokenizerModel);
        return tokenizerME.tokenize(text);
    }

    public String[] detectPos(String text) throws ModelNotFoundException, IOException {
        POSModel posModel = models.loadPosModel();
        POSTaggerME posTaggerME = new POSTaggerME(posModel);
        String[] tokens = detectTokens(text);
        return posTaggerME.tag(tokens);
    }

    public String[] detectChunker(String text) throws ModelNotFoundException, IOException {
        ChunkerModel chunkerModel = models.loadChunkerModel();
        ChunkerME chunkerME = new ChunkerME(chunkerModel);
        return chunkerME.chunk(detectTokens(text), detectPos(text));
    }

    public Span[] getChunkSpan(String text) throws ModelNotFoundException, IOException {
        ChunkerModel chunkerModel = models.loadChunkerModel();
        ChunkerME chunkerME = new ChunkerME(chunkerModel);
        return chunkerME.chunkAsSpans(detectTokens(text), detectPos(text));
    }

}
