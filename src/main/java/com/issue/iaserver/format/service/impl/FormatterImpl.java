package com.issue.iaserver.format.service.impl;

import com.issue.iaserver.format.model.RichDescription;
import com.issue.iaserver.format.service.Formatter;

public class FormatterImpl implements Formatter {
    @Override
    public RichDescription getRichDescription(String description) {
        return null;
    }

    @Override
    public String getBriefDescription(String description) {
        int firstLineEndPoint = description.indexOf('\n');
        if(firstLineEndPoint > 100){
            return description.substring(0, 100) + "...";
        }
        else
            return description.substring(0, firstLineEndPoint+1) + "...";
    }

    @Override
    public String format(String description) {
        description = description.replaceAll("\n", "\r\n");
        description = description.replace((char)65533, '\t');
        return description;
    }

}
