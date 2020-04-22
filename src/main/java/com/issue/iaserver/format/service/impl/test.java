package com.issue.iaserver.format.service.impl;

import com.issue.iaserver.format.model.RichDescription;
import com.issue.iaserver.format.service.Formatter;
import com.issue.iaserver.format.service.FormatterFactory;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.List;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args) {

        new test().readFile();
//        new test().tryMethods();
    }

    public void tryMethods(){
        String s = "  \n \n\t\t\t\na\n\nsdi\t\t\nbc";
//        System.out.println(s.trim());


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
                description = formatter.format(description);

                RichDescription richDescription = formatter.getRichDescription(description);
                System.out.println(richDescription.getRichText());



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
