package com.issue.iaserver.format.service.impl;

import com.issue.iaserver.format.model.DescArea;
import com.issue.iaserver.format.model.RichDescription;

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


    private void blockingRichDescription(){
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
        List<DescArea> textAreas = getAreasOfType("text");

        for (DescArea descArea : textAreas) {
            String content = descArea.getContent();
            String regex = "(when|during|after).*? \\w+[.,;:]";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(content);
            if (m.find()) {
                scenarioFound = true;
                content = insertTagToText(content, m.start(), m.end() - 1, SCENARIO_START_TAG, SCENARIO_END_TAG);
                descArea.setContent(content);
            }
        }
        if(!scenarioFound){
            for (DescArea descArea : textAreas) {
                String content = descArea.getContent();
                String regex = "if.*? \\w+[.,;:]";
                Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher m = pattern.matcher(content);
                if (m.find()) {
                    scenarioFound = true;
                    content = insertTagToText(content, m.start(), m.end() - 1, SCENARIO_START_TAG, SCENARIO_END_TAG);
                    descArea.setContent(content);
                }
            }
        }

    }

    private void findSolution(){
        List<DescArea> textAreas = getAreasOfType("text");

        wrapTextForSolution(textAreas, "(modify|suggest|maybe|should|we can|it's better to).*? \\w+[.,;:]");

        if(!solutionFound){
            wrapTextForSolution(textAreas, "need to.*? \\w+[.,;:]");
        }

        if(!scenarioFound && !solutionFound){
            wrapTextForSolution(textAreas, "if.*? \\w+[.,;:]");
        }

    }

    private void wrapTextForSolution(List<DescArea> textAreas, String regex){
        for (DescArea descArea : textAreas) {
            String content = descArea.getContent();
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher m = pattern.matcher(content);
            if (m.find()) {
                solutionFound = true;
                content = insertTagToText(content, m.start(), m.end() - 1, SOLUTION_START_TAG, SOLUTION_END_TAG);
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

        return s.substring(startPoint, endPoint) + "\n";
    }

    private List<DescArea> getAreasOfType(String type) {
        return richDescription.getDescAreas().stream()
                .filter(descArea -> descArea.getType().equals(type))
                .collect(Collectors.toList());
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
