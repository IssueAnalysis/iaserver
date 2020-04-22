package com.issue.iaserver.format.model;

import java.util.ArrayList;
import java.util.List;


public class RichDescription {
    private  List<DescArea> descAreas;
    private String richText;

    public RichDescription() {
        richText = "";
        descAreas = new ArrayList<>();
    }

    public RichDescription(List<DescArea> descAreas) {
        this.descAreas = descAreas;
    }

    public List<DescArea> getDescAreas() {
        return descAreas;
    }

    public void setDescAreas(List<DescArea> descAreas) {
        this.descAreas = descAreas;
    }

    public void addDescArea(DescArea descArea){
        this.descAreas.add(descArea);
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    public void appendRichText(String richText){
        this.richText += richText;
    }
}
