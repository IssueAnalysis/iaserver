package com.issue.iaserver.extractor.server;

import com.issue.iaserver.extractor.nlpUtil.Detector;
import com.issue.iaserver.extractor.model.ChunkTag;
import com.issue.iaserver.extractor.pojos.ChunkTagDic;
import com.issue.iaserver.extractor.pojos.SpanInCn;
import opennlp.tools.util.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextChunker implements Chunker {

    private final Detector detector;
    private final ChunkTagDic chunkTagDic;

    @Autowired
    public TextChunker(ChunkTagDic chunkTagDic, Detector detector) {
        this.detector = detector;
        this.chunkTagDic = chunkTagDic;
    }


    private Logger logger = LoggerFactory.getLogger(TextChunker.class);


    @Override
    public SpanInCn[] chunkAsSpanInCn(String text) {
        String[] tokens = detector.detectTokens(text);
        Span[] spans = detector.getChunkSpan(text);
        SpanInCn[] spanInCns = new SpanInCn[spans.length];
        int index = 0;
        int i;
        for (Span span : spans) {
            // translate
            int length = span.length();
            String[] subTokens = new String[length];
            for (i = 0; i < length; i++) {
                subTokens[i] = tokens[span.getStart() + i];
            }
            spanInCns[index] = new SpanInCn(span, subTokens, chunkTagDic.enToCn(span.getType()));
            index++;
        }
        return spanInCns;
    }

    @Override
    public SpanInCn[] getAllNPChunks(String text) {
        SpanInCn[] spans = chunkAsSpanInCn(text);
        int npChunksCount = 0;
        for (SpanInCn spanInCn : spans) {
            if (spanInCn.getType().equals(ChunkTag.NOUN_PHASE.getType())) {
                npChunksCount++;
            }
        }
        SpanInCn[] npChunks = new SpanInCn[npChunksCount];
        npChunksCount = 0;
        for (SpanInCn spanInCn : spans) {
            if (spanInCn.getType().equals(ChunkTag.NOUN_PHASE.getType())) {
                npChunks[npChunksCount] = spanInCn;
                npChunksCount++;
            }
        }
        return npChunks;
    }

    @Override
    public SpanInCn[] getAllVPChunks(String text) {
        SpanInCn[] spans = chunkAsSpanInCn(text);
        int vpChunksCount = 0;
        for (SpanInCn spanInCn : spans) {
            if (spanInCn.getType().equals(ChunkTag.VERB_PHASE.getType())) {
                vpChunksCount++;
            }
        }
        SpanInCn[] vpChunks = new SpanInCn[vpChunksCount];
        vpChunksCount = 0;
        for (SpanInCn spanInCn : spans) {
            if (spanInCn.getType().equals(ChunkTag.VERB_PHASE.getType())) {
                vpChunks[vpChunksCount] = spanInCn;
                vpChunksCount++;
            }
        }
        return vpChunks;
    }
}
