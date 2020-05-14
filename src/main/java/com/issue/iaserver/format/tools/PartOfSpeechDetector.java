package com.issue.iaserver.format.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class PartOfSpeechDetector {
    private POSModel model;
    private WhitespaceTokenizer whitespaceTokenizer;

    private POSTaggerME tagger;


    public PartOfSpeechDetector() throws IOException {
        InputStream inputStream = new FileInputStream("src/main/resources/models/en-pos-maxent.bin");
        model = new POSModel(inputStream);
        whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;
        tagger = new POSTaggerME(model);
    }

    public boolean isFirstWordVerb(String sentence){
        String [] tokens = whitespaceTokenizer.tokenize(sentence);
        String part = tagger.tag(tokens)[0];
        System.out.println(tokens[0] + ": " + part);
        return part.equals("VB");
    }


    public void detect(String sentence) {
        String[] tokens = whitespaceTokenizer.tokenize(sentence);

        //Generating tags
        String[] tags = tagger.tag(tokens);

        //Instantiating the POSSample class
        POSSample sample = new POSSample(tokens, tags);
        System.out.println(sample.toString());

        //Probabilities for each tag of the last tagged sentence.
        double[] probs = tagger.probs();
        System.out.println("  ");

        //Printing the probabilities
        for (int i = 0; i < probs.length; i++)
            System.out.println(probs[i]);

    }

    public static void main(String args[]) throws Exception {

        //Loading Parts of speech-maxent model

        //Creating an object of WhitespaceTokenizer class

        //Tokenizing the sentence
        String sentence = "Let's convert this class to use SLF4J";//"Add logs like \"BLOCK* No live nodes contain block blk_1073741825_1001, last datanode contain it is node: 127.0.0.1:65341\" in BlockStateChange should help to identify which datanode should be fixed first to recover missing blocks.";
        boolean b = new PartOfSpeechDetector().isFirstWordVerb(sentence);
        System.out.println(b);
    }
}