package com.issue.iaserver.webserver.service.impl;

import com.iaserver.data.mongdb.CSVitem;
import com.iaserver.data.mysql.entity.CSVDO;
import com.iaserver.data.service.OperateFileService;
import com.iaserver.data.service.impl.OperateFileServiceImpl;
import com.issue.iaserver.webserver.model.Issue;
import com.issue.iaserver.webserver.service.IssueService;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IssueServiceImpl implements IssueService {

    private OperateFileService operateFileService = new OperateFileServiceImpl();

    @Override
    public List<Issue> getAllIssues() {
        List<CSVDO> csvdos = operateFileService.getAllCSV();

        List<Issue> issues = new ArrayList<>();
        for(CSVDO csvdo : csvdos){
            long csvId = csvdo.getId();
            List<CSVitem> csvItems = operateFileService.getCSVitemByCSVid(csvId);
            for(CSVitem item : csvItems){
                Issue issue = new Issue(item.getSummary(), item.getDescription(), item.getIntension(), item.getConsideration());
                issues.add(issue);
            }
        }
        return issues;
    }

    public static void main(String[] args){

        IssueServiceImpl issueService = new IssueServiceImpl();

        issueService.readFile();

    }

    public List<String[]> readAll(Reader reader) throws IOException {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = new ArrayList<>();
        list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    public void readFile(){
        try {

            BufferedReader br = new BufferedReader(new FileReader("webserver/src/main/resources/datamini.csv"));

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
