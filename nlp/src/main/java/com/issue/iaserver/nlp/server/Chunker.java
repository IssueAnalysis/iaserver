package com.issue.iaserver.nlp.server;


import com.issue.iaserver.nlp.pojos.SpanInCn;
import org.springframework.stereotype.Service;

/**
 * 文章分块器
 */
@Service
public interface Chunker {

    SpanInCn[] chunkAsSpanInCn(String text);

}
