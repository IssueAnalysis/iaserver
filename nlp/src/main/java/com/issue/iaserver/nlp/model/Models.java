package com.issue.iaserver.nlp.model;

import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class Models {


    private Properties properties;

    public Models(){
        try {
            initProperties();
        } catch (IOException e) {
            Logger modelLogger = LoggerFactory.getLogger(Models.class);
            modelLogger.error(e.getMessage());
        }
    }

    private void initProperties() throws IOException {
        String propertiesPath = "models/model.properties";
        properties = new Properties();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesPath)) {
            if(inputStream != null){
                properties.load(inputStream);
            }
        }
    }

    private InputStream getModelStream(ModelType modelType) throws ModelNotFoundException {
        String rootPath = properties.getProperty("root");
        String modelFileName = properties.getProperty(modelType.getType(), "");
        if(modelFileName.equals("")){
            // 模型不存在
            throw new ModelNotFoundException("Model doesn't exist!");
        }else{
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(rootPath + modelFileName);
        }

    }

    public SentenceModel loadSentenceModel() throws ModelNotFoundException, IOException {
        return new SentenceModel(getModelStream(ModelType.SENTENCE_MODEL));
    }

    public TokenizerModel loadTokenizerModel() throws ModelNotFoundException, IOException {
        return new TokenizerModel(getModelStream(ModelType.TOKEN_MODEL));
    }

    public POSModel loadPosModel() throws ModelNotFoundException, IOException {
        return new POSModel(getModelStream(ModelType.POS_MODEL));
    }
    
    public ChunkerModel loadChunkerModel() throws ModelNotFoundException, IOException {
        return new ChunkerModel(getModelStream(ModelType.CHUNKER_MODEL));
    }

}
