package com.issue.iaserver.format.service.impl;

import com.issue.iaserver.format.model.DescArea;
import com.issue.iaserver.format.model.RichDescription;
import com.issue.iaserver.format.service.Formatter;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class FormatterImpl implements Formatter {

    private static final int BRIEF_DESC_LENGTH = 100;

    @Override
    public RichDescription getRichDescription(String description) {
        RichDescription richDescription = new RichDescription();
        String delimiter =  "\\{code(:\\w+)?}";
        Pattern pattern = Pattern.compile(delimiter);
        String[] areas = pattern.split(description);

        for(int areaNum = 0; areaNum < areas.length;areaNum++){
            String content = areas[areaNum];
            DescArea descArea;
            if(areaNum%2 == 0){         //偶数段为普通文字
                content = trimText(content);
                if(content.length() == 0)
                    continue;
                descArea = new DescArea("text", content);
            }
            else{
                content = "<pre><code>" + content + "</code></pre>\n";
                descArea = new DescArea("code", content);
            }
            richDescription.addDescArea(descArea);
            richDescription.appendRichText(descArea.getContent());
        }
        return richDescription;
    }

    @Override
    public String getBriefDescription(String description) {
        int firstLineEndPoint = description.indexOf('\n');
        if (firstLineEndPoint > BRIEF_DESC_LENGTH) {
            return description.substring(0, BRIEF_DESC_LENGTH) + "...";
        } else if (firstLineEndPoint == -1) {
            if (description.length() <= BRIEF_DESC_LENGTH)
                return description;
            else
                return description.substring(0, BRIEF_DESC_LENGTH) + "...";
        } else
            return description.substring(0, firstLineEndPoint + 1) + "...";
    }

    /*
     1. 换行符变成\n
     2. 特殊字符65533变成tab
     */
    @Override
    public String format(String description) {
        description = description.replaceAll("\r\n", "\n")
                                .replaceAll("\r", "\n")
//                                .replaceAll("\n", "\r\n")
                                .replace((char) 65533, '\t');
        return description;
    }


    private String trimText(String s){      //去掉多余的行
        int i = 0, j = 0, len = s.length();
        while(j < len){      //去掉头部多余行
            char c = s.charAt(j);
            if(c == '\n'){
                j++;
                i = j;
                continue;
            }
            if(c == '\t' || c == ' '){
                j++;
            }
            else
                break;
        }

        final int startPoint = i;

        i = len-1;
        j = len-1;

        while(j >= 0){      //去掉尾部多余行
            char c = s.charAt(j);
            if(c == '\n'){
                j--;
                i = j;
                continue;
            }
            if(c == '\t' || c == ' '){
                j--;
            }
            else
                break;
        }

        final int endPoint = i+1;

        return s.substring(startPoint, endPoint) + "\n";
    }
}
