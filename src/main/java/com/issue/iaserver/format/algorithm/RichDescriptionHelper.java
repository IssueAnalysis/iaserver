package com.issue.iaserver.format.algorithm;

import com.issue.iaserver.format.model.DescArea;
import com.issue.iaserver.format.model.RichDescription;
import com.issue.iaserver.format.tools.PartOfSpeechDetector;
import com.issue.iaserver.format.tools.SentenceDetector;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RichDescriptionHelper {
    private RichDescription richDescription;

    private String description;

    private boolean scenarioFound = false;
    private boolean solutionFound = false;

    //代码
    private static final String CODE_START_TAG = "<pre><code>";
    private static final String CODE_END_TAG = "</code></pre>";

    //场景
    private static final String SCENARIO_START_TAG = "<scenario>";
    private static final String SCENARIO_END_TAG = "</scenario>";

    //解决方案
    private static final String SOLUTION_START_TAG = "<solution>";
    private static final String SOLUTION_END_TAG = "</solution>";

    private SentenceDetector sentenceDetector;
    private PartOfSpeechDetector partOfSpeechDetector;

    public RichDescriptionHelper(String description) {
        this.richDescription = new RichDescription();
        this.description = description;
    }

    public RichDescription prepareRichDescription(){
        blockingRichDescription();
        findScenario();
        findSolution();
        richDescription.generateRichText();
        return richDescription;
    }


    public void blockingRichDescription(){
        String delimiter = "\\{(code(:.*?)?|noformat|quote)}";
        Pattern pattern = Pattern.compile(delimiter);
        String[] areas = pattern.split(description);

        for (int areaNum = 0; areaNum < areas.length; areaNum++) {
            String content = areas[areaNum];
            DescArea descArea;
            if (areaNum % 2 == 0) {         //偶数段为普通文字
                content = trimText(content);
                if (content.length() == 0)
                    continue;
                descArea = new DescArea("text", content);
            } else {
                content = CODE_START_TAG + content + CODE_END_TAG + "\n";
                descArea = new DescArea("code", content);
            }
            richDescription.addDescArea(descArea);
        }
    }

    private void findScenario() {

        wrapText("(when|during|after).*? \\w+(?=[.,;:])", "scenario");
        if(!scenarioFound){
            wrapText("if.*? \\w+(?=[.,;:])", "scenario");
        }

    }

    private void findSolution(){

        wrapText("(modify|suggest|maybe|should|we can|it's better to).*? \\w+(?=[.,;:])", "solution");

        if(!solutionFound){
            wrapText("need to.*? \\w+(?=[.,;:])", "solution");
        }

        if(!scenarioFound && !solutionFound){
            wrapText("if.*? \\w+(?=[.,;:])", "solution");
        }

        if(!solutionFound){
            markImperativeSentenceAsSolution();
        }

    }

    private void markImperativeSentenceAsSolution(){
        try {
            sentenceDetector = new SentenceDetector();
            partOfSpeechDetector = new PartOfSpeechDetector();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        List<DescArea> textAreas = richDescription.getAreasOfType("text");
        for(DescArea descArea : textAreas){
            String content = descArea.getContent();
            Span[] spans = sentenceDetector.detect(content);
            for(Span span : spans){
                String sentence = content.substring(span.getStart(), span.getEnd());
                boolean isImperative = partOfSpeechDetector.isFirstWordVerb(sentence);
                if(isImperative){
                    //TODO
                }
            }

        }

    }

    private void wrapText(String regex, String type){
        List<DescArea> textAreas = richDescription.getAreasOfType("text");

        for (DescArea descArea : textAreas) {
            String content = descArea.getContent();
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(content);
            if (m.find()) {
                if(type.equals("scenario")){
                    scenarioFound = true;
                    content = insertTagToText(content, m.start(), m.end(), SCENARIO_START_TAG, SCENARIO_END_TAG);
                }
                else if(type.equals("solution")){
                    solutionFound = true;
                    content = insertTagToText(content, m.start(), m.end(), SOLUTION_START_TAG, SOLUTION_END_TAG);
                }
                descArea.setContent(content);
            }
        }
    }


    private String trimText(String s) {      //去掉多余的行
        int i = 0, j = 0, len = s.length();
        while (j < len) {      //去掉头部多余行
            char c = s.charAt(j);
            if (c == '\n') {
                j++;
                i = j;
                continue;
            }
            if (c == '\t' || c == ' ') {
                j++;
            } else
                break;
        }

        final int startPoint = i;

        i = len - 1;
        j = len - 1;

        while (j >= 0) {      //去掉尾部多余行
            char c = s.charAt(j);
            if (c == '\n') {
                j--;
                i = j;
                continue;
            }
            if (c == '\t' || c == ' ') {
                j--;
            } else
                break;
        }

        final int endPoint = i + 1;

        String result = s.substring(startPoint, endPoint) + "\n";
        int l;
        do{                 //去掉中间多余的行
            l = result.length();
            result = result.replaceAll("\n\n", "\n");
        }while(l != result.length());

        return result;
    }



    private String insertTagToText(String text, final int startPos, final int endPos, final String startTag, final String endTag) {
        StringBuilder sb = new StringBuilder();
        sb.append(text.substring(0, startPos));
        sb.append(startTag);
        sb.append(text.substring(startPos, endPos));
        sb.append(endTag);
        sb.append(text.substring(endPos));
        return sb.toString();
    }




    public RichDescription getRichDescription() {
        return richDescription;
    }

    public void setRichDescription(RichDescription richDescription) {
        this.richDescription = richDescription;
    }
}
