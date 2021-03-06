package com.issue.iaserver.extractor.util;

import java.io.*;


public class FileUtil {
    public String getTestText() throws IOException {
        File file = new File(this.getClass().getResource("/").getPath() + "/testTexts");
        if(!file.exists()){
            return "this is a test!";
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String t = bufferedReader.readLine();
        while(t != null){
            stringBuilder.append(t).append("\r\n");
            t = bufferedReader.readLine();
        }

        return stringBuilder.toString();
    }
}
