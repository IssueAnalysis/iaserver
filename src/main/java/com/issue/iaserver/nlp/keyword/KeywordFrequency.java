package com.issue.iaserver.nlp.keyword;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

// 关键词整个文本频率统计
// 使用前缀树的方式
@Component
@Scope("singleton")
public class KeywordFrequency {

    private Node root;
    private int totalIssueCount;

    static class Node{
        char val;
        List<Node> nextList;
        int freq;
        Node(char val){
            this.val = val;
            nextList = new LinkedList<>();
            this.freq = 0;
        }
    }

    KeywordFrequency(){
        
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
