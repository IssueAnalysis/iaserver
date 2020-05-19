package com.issue.iaserver.format.algorithm;

import com.issue.iaserver.format.model.DescArea;
import com.issue.iaserver.format.service.Formatter;
import com.issue.iaserver.format.service.FormatterFactory;
import com.issue.iaserver.format.tools.SentenceDetector;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import opennlp.tools.util.Span;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MachineLearning {

    private static final String INPUT_CSV_PATH = "src/main/resources/datamini.csv";
    private static final String DATASET_PATH = "src/main/resources/ml_data/data1.csv";

    private Formatter formatter = FormatterFactory.getFormatterService();
    private SentenceDetector sentenceDetector = new SentenceDetector();

    public MachineLearning() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        new MachineLearning().generateDataset();

    }

    private void generateDataset() {

        List<String> sentences = Objects.requireNonNull(getDescriptionsFromCsv(INPUT_CSV_PATH))
                .stream()
                .map(this::getSentencesFromDescription)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
//        for (int i = 0; i < sentences.size(); i++) {
//            System.out.println("第" + i + "句: " + sentences.get(i));
//        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(DATASET_PATH));
            CSVWriter csvWriter = new CSVWriter(bw, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);
            for(String s : sentences){
                String [] line = new String[2];
                line[0] = s;
                line[1] = String.valueOf(0);
                csvWriter.writeNext(line);
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> getDescriptionsFromCsv(String path) {
        try {

            BufferedReader br = new BufferedReader(new FileReader(path));

            CSVReader csvReader = new CSVReader(br);
            List<String[]> csvFile = csvReader.readAll();
            br.close();
            csvReader.close();

            String[] headers = csvFile.get(0);
            int descriptionIndex = findIndexForTarget(headers, "Description");
            if (descriptionIndex == -1)
                return null;

            List<String> descriptions = new ArrayList<>();
            for (int i = 1; i < csvFile.size(); i++) {
                String[] data = csvFile.get(i);
                String description = data[descriptionIndex];
                descriptions.add(description);
            }

            return descriptions;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getSentencesFromDescription(String description) {

        List<String> sentences = new ArrayList<>();

        description = formatter.format(description);
        RichDescriptionHelper richDescriptionHelper = new RichDescriptionHelper(description);
        richDescriptionHelper.blockingRichDescription();
        List<DescArea> textAreas = richDescriptionHelper.getRichDescription().getAreasOfType("text");

        for (DescArea descArea : textAreas) {
            String text = descArea.getContent();
            Span[] spans = sentenceDetector.detect(text);
            for (Span span : spans) {
                String sentence = text.substring(span.getStart(), span.getEnd());
                List<String> subsentences = Arrays.stream(sentence.split(", "))     //再按逗号和换行分一次
                        .map(s -> s.split("\n"))
                        .flatMap(Arrays::stream)
                        .map(String::trim)      //过滤掉空字符串
                        .filter(s -> s.length() > 0)
                        .map(s -> s.replaceAll(",", " "))
                        .collect(Collectors.toList());
                sentences.addAll(subsentences);
            }
        }
        return sentences;
    }


    //找到目标条目的下标
    private int findIndexForTarget(String[] headers, String target) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(target))
                return i;
        }
        return -1;
    }
}
