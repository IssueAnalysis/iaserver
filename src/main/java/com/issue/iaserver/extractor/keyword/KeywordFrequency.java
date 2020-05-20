package com.issue.iaserver.extractor.keyword;

import com.issue.iaserver.utils.ClassPathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

// 关键词整个文本频率统计
// 使用前缀树的方式
@Component
@Scope("singleton")
public class KeywordFrequency {

    private Logger logger = LoggerFactory.getLogger(KeywordFrequency.class);
    private Node root;
    private int totalIssueCount;

    static class Node{
        char val;
        List<Node> nextList;
        int freq;
        Node(char val){
            this.val = val;
            this.nextList = new LinkedList<>();
            this.freq = 0;
        }
        Node(char val, int freq){
            this.val = val;
            this.freq = freq;
            this.nextList = new LinkedList<>();
        }
    }

    KeywordFrequency(){
        this.root = new Node(' ');
        init();
    }

    private void init(){
        File file = new File(ClassPathUtil.getProjectRootPath() + "/temp/keywordFreq.dat");
        try{
            if(!file.exists()){
                return;
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            initIterate(root, bufferedReader);
            bufferedReader.close();
        }catch (IOException e){
            logger.error(e.getMessage());
        }
    }

    private Node initIterate(Node cur, BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        if(line != null){
            if(line.equals("")) return cur;
            String[] temp = line.split(" ");
            for(String str : temp){
                if(!str.equals("")){
                    cur.nextList.add(
                            initIterate(
                                new Node(str.split(":")[0].charAt(0), Integer.parseInt(str.split(":")[1])),
                                    bufferedReader
                            )
                            );
                }
            }
        }
        return cur;
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void updateData(){
        File file = new File(ClassPathUtil.getProjectRootPath() + "/temp/keywordFreq.dat");
        try {
            if(!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            // clean old file
            bufferedWriter.write("");
            updateIterate(root,bufferedWriter);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage() + file.getPath());
        }
    }

    private void updateIterate(Node cur, BufferedWriter bufferedWriter) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        for(Node n : cur.nextList){
            stringBuffer.append(n.val).append(":").append(n.freq).append(" ");
        }
        bufferedWriter.append(stringBuffer.toString());
        bufferedWriter.newLine();
        stringBuffer = null;
        for(Node n : cur.nextList){
            updateIterate(n, bufferedWriter);
        }
        bufferedWriter.newLine();
    }

    public int getTotalIssueCount() {
        return totalIssueCount;
    }

    public void addWord(String word, int addFreq){
        char[] chars = word.toCharArray();
        Node cur = root;
        int i;
        boolean b;
        for(char c : chars){
            b = false;
            for(i = 0; i < cur.nextList.size();i++){
                if(cur.nextList.get(i).val == c){
                    b = true;
                    cur = cur.nextList.get(i);
                    break;
                }
            }
            if(!b){
                Node n = new Node(c);
                cur.nextList.add(n);
                cur = n;
            }
        }
        cur.freq += addFreq;
        totalIssueCount += addFreq;
    }

    public void addWord(String word){
        addWord(word,1);
    }

    public int getFreq(String word){
        char[] chars = word.toCharArray();
        Node cur = root;
        int i = 0;
        boolean b;
        for(char c : chars){
            b = false;
            for(i = 0; i < cur.nextList.size(); i++){
                if(cur.nextList.get(i).val == c){
                    cur = cur.nextList.get(i);
                    b = true;
                    break;
                }
            }
            if(!b){
                return 0;
            }
        }
        return cur.freq;
    }
}
