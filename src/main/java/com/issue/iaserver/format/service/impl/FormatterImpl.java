package com.issue.iaserver.format.service.impl;

import com.issue.iaserver.format.model.RichDescription;
import com.issue.iaserver.format.service.Formatter;
import org.springframework.stereotype.Service;

@Service
public class FormatterImpl implements Formatter {

    private static final int BRIEF_DESC_LENGTH = 100;

    @Override
    public RichDescription getRichDescription(String description) {
        return null;
    }

    @Override
    public String getBriefDescription(String description) {
        int firstLineEndPoint = description.indexOf('\n');
        if(firstLineEndPoint > BRIEF_DESC_LENGTH){
            return description.substring(0, BRIEF_DESC_LENGTH) + "...";
        }
        else if(firstLineEndPoint == -1){
            if (description.length() <= BRIEF_DESC_LENGTH)
                return description;
            else
                return description.substring(0, BRIEF_DESC_LENGTH) + "...";
        }
        else
            return description.substring(0, firstLineEndPoint+1) + "...";
    }

    @Override
    public String format(String description) {
        description = description.replaceAll("\r\n", "\n")
                                .replaceAll("\n", "\r\n")
                                .replace((char)65533, '\t');
        return description;
    }

}
