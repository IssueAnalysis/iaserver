package com.issue.iaserver.format.service.impl;

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

            BufferedReader br = new BufferedReader(new FileReader("com/issue/iaserver/webserver/src/main/resources/datamini.csv"));

            CSVReader csvReader = new CSVReader(br);
            List<String[]> csvFile = csvReader.readAll();
            br.close();
            csvReader.close();

            String[] headers = csvFile.get(0);


            int summaryIndex = findIndexForTarget(headers, "Summary");
            int descriptionIndex = findIndexForTarget(headers, "Description");

            int count = 0;
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
                System.out.println(summary);
                System.out.println(description);
                System.out.println(++count);
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
