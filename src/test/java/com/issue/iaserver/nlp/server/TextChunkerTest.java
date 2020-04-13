package com.issue.iaserver.nlp.server;

import com.issue.iaserver.Main;
import com.issue.iaserver.nlp.pojos.SpanInCn;
import com.issue.iaserver.nlp.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
class TextChunkerTest {


    @Autowired
    private Chunker chunker;

    @Test
    void chunkAsSpanInCn() throws IOException {
        String s = new FileUtil().getTestText();
        assert s != null;
        SpanInCn[] spanInCns = chunker.chunkAsSpanInCn(s);
        for(SpanInCn spanInCn : spanInCns){
            System.out.println(spanInCn.toString());
        }
    }

    @Test
    void getAllNPChunks() throws IOException {
        String s = new FileUtil().getTestText();
        SpanInCn[] spanInCns = chunker.getAllNPChunks(s);
        for(SpanInCn spanInCn : spanInCns){
            System.out.println(spanInCn.toString());
        }
    }

    @Test
    void getAllVPChunks() throws IOException {
        String s = new FileUtil().getTestText();
        SpanInCn[] spanInCns = chunker.getAllVPChunks(s);
        for(SpanInCn spanInCn : spanInCns){
            System.out.println(spanInCn.toString());
        }
    }
}