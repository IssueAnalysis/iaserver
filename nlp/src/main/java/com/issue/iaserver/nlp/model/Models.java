package com.issue.iaserver.nlp.model;

import opennlp.tools.sentdetect.SentenceModel;
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

            // TODO 尝试一个更好的解决方案
            e.printStackTrace();
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

}
