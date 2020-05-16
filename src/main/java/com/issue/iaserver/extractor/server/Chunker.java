package com.issue.iaserver.extractor.server;


import com.issue.iaserver.extractor.pojos.SpanInCn;
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

    /**
     * 将文本分块，并获得类型为NP的块
     * @param text 英文文本
     * @return Span数组，用于表示文本的分块
     * 若无法分块则返回数组长度为0
     */
    SpanInCn[] getAllNPChunks(String text);

    /**
     * 将文本分块，并获得类型为VP的块
     * @param text 英文文本
     * @return Span数组，用于表示文本的块
     * 若无法分块则返回数组长度为0
     */
    SpanInCn[] getAllVPChunks(String text);

}
