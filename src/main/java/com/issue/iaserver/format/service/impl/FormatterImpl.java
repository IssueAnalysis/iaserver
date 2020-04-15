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
        return description;
    }

    @Override
    public String format(String description) {
        return description;
    }
}
