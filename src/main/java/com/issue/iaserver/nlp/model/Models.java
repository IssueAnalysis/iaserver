package com.issue.iaserver.nlp.model;

import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Configuration
@PropertySource("classpath:models/model.properties")
@Component
public class Models {

    @Value("${root}")
    private String root;

    @Value("${sentence}")
    private String sentenceModel;

    @Value("${token}")
    private String tokenModel;

    @Value("${pos}")
    private String posModel;

    @Value("${chunker}")
    private String chunkerModel;

    @Value("${lemmatizer}")
    private String lemmatizerModel;

    @Value("${name.location}")
    private String nameFinderLocationModel;

    @Value("${name.money}")
    private String nameFinderMoneyModel;

    @Value("${name.organization}")
    private String nameFinderOrganizationModel;

    @Value("${name.percentage}")
    private String nameFinderPercentageModel;

    @Value("${name.person}")
    private String nameFinderPersonModel;

    @Value("${name.time}")
    private String nameFinderTimeModel;

    @Value("${name.date")
    private String nameFinderDateModel;


    private InputStream getModelStream(String model) throws ModelNotFoundException {
        String rootPath = root;
        if(model.equals("")){
            // 模型不存在
            throw new ModelNotFoundException("Model doesn't exist!");
        }else{
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(rootPath + model);
        }

    }

    public SentenceModel loadSentenceModel() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(sentenceModel);
        SentenceModel sentenceModel1 =  new SentenceModel(inputStream);
        inputStream.close();
        return sentenceModel1;
    }

    public TokenizerModel loadTokenizerModel() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(tokenModel);
        TokenizerModel tokenizerModel1 = new TokenizerModel(inputStream);
        inputStream.close();
        return tokenizerModel1;
    }

    public POSModel loadPosModel() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(posModel);
        POSModel posModel1 = new POSModel(inputStream);
        inputStream.close();
        return posModel1;
    }
    
    public ChunkerModel loadChunkerModel() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(chunkerModel);
        ChunkerModel chunkerModel1 = new ChunkerModel(inputStream);
        inputStream.close();
        return chunkerModel1;
    }

    public DictionaryLemmatizer loadLemmatizer() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(lemmatizerModel);
        DictionaryLemmatizer dictionaryLemmatizer = new DictionaryLemmatizer(getModelStream(lemmatizerModel));
        inputStream.close();
        return dictionaryLemmatizer;
    }

    public TokenNameFinderModel loadLocationNameFinder() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(nameFinderLocationModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
        inputStream.close();
        return tokenNameFinderModel;
    }

    public TokenNameFinderModel loadMoneyNameFinder() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(nameFinderMoneyModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
        inputStream.close();
        return tokenNameFinderModel;
    }

    public TokenNameFinderModel loadOrganizationNameFinder() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(nameFinderOrganizationModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
        inputStream.close();
        return tokenNameFinderModel;
    }

    public TokenNameFinderModel loadPercentageNameFinder() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(nameFinderPercentageModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
        inputStream.close();
        return tokenNameFinderModel;
    }

    public TokenNameFinderModel loadPersonNameFinder() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(nameFinderPersonModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
        inputStream.close();
        return tokenNameFinderModel;
    }

    public TokenNameFinderModel loadTimeNameFinder() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(nameFinderTimeModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
        inputStream.close();
        return tokenNameFinderModel;
    }

    public TokenNameFinderModel loadDateNameFinder() throws ModelNotFoundException, IOException {
        InputStream inputStream = getModelStream(nameFinderTimeModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(inputStream);
        inputStream.close();
        return tokenNameFinderModel;
    }

}
