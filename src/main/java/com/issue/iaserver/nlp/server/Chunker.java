package com.issue.iaserver.nlp.server;


import com.issue.iaserver.nlp.pojos.SpanInCn;
import org.springframework.stereotype.Service;

/**
 * 文章分块器
 */
@Service
public interface Chunker {

    /**
     * 将文本分块，并将每块的type额外翻译为中文
     * @param text 英文文本
     * @return Span数组，用于表示文本的分块
     * 若无法分块则返回数组长度为0
     *
     *
     */
    SpanInCn[] chunkAsSpanInCn(String text);

    SpanInCn[] getAllNPChunks(String text);

    SpanInCn[] getAllVPChunks(String text);

}
