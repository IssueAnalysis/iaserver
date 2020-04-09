package com.issue.iaserver.nlp.server;

import com.issue.iaserver.nlp.demo.Detector;
import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.pojos.PosTags;
import com.issue.iaserver.nlp.pojos.SpanInCn;
import opennlp.tools.util.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TextChunker implements Chunker{


    private Logger logger = LoggerFactory.getLogger(TextChunker.class);
    private final Detector detector;

    @Autowired
    public TextChunker(Detector detector) {
        this.detector = detector;
    }

    /**
     * 将文本分块，并将每块的type额外翻译为中文
     * @param text 英文文本
     * @return Span数组，用于表示文本的分块
     * 若无法分块则返回数组长度为0
     *
     *
     */
    @Override
    public SpanInCn[] chunkAsSpanInCn(String text) {
        try{
            String[] tokens = detector.detectTokens(text);
            Span[] spans = detector.getChunkSpan(text);
            SpanInCn[] spanInCns = new SpanInCn[spans.length];
            int index = 0;
            int i;
            for(Span span: spans){
                // translate
                int length = span.length();
                String[] subTokens = new String[length];
                for(i = 0; i < length; i++){
                    subTokens[i] = tokens[span.getStart() + i];
                }
                spanInCns[index] = new SpanInCn(span, subTokens, PosTags.enToCn(span.getType()));
                index++;
            }
            return spanInCns;
        } catch (ModelNotFoundException | IOException e) {
            logger.error(e.getMessage());
        }
        return new SpanInCn[0];
    }
}
