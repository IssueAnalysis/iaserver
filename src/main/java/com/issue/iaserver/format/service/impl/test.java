package com.issue.iaserver.format.service.impl;

import com.issue.iaserver.format.model.RichDescription;
import com.issue.iaserver.format.service.Formatter;
import com.issue.iaserver.format.service.FormatterFactory;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class test {

    public static void main(String[] args) {

        new test().readFile();
    }


    public void readFile(){
        try {

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/datamini.csv"));

            CSVReader csvReader = new CSVReader(br);
            List<String[]> csvFile = csvReader.readAll();
            br.close();
            csvReader.close();

            String[] headers = csvFile.get(0);


            int summaryIndex = findIndexForTarget(headers, "Summary");
            int descriptionIndex = findIndexForTarget(headers, "Description");

            for(int i = 1;i < csvFile.size();i++){
                String[] data = csvFile.get(i);
                String summary = "";
                if(summaryIndex != -1) {
                    summary = data[summaryIndex];
                }
                String description = "";
                if(descriptionIndex != -1){
                    description = data[descriptionIndex];
                }
//                System.out.println(summary);
//                System.out.println(description);


                Formatter formatter = FormatterFactory.getFormatterService();
                RichDescription richDescription = formatter.getRichDescription(description);
                description = formatter.format(description);
                String[] descriptionLines = description.split("\r");
                System.out.println(descriptionLines.length);
                System.out.println(description);
//                String target = descriptionLines[8];
//                char targetChar = target.charAt(target.length()-1);
//                System.out.println((int)targetChar);



                break;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //找到目标条目的下标
    private int findIndexForTarget(String[] headers, String target){
        for(int i = 0;i < headers.length;i++){
            if(headers[i].equals(target))
                return i;
        }
        return -1;
    }


}
