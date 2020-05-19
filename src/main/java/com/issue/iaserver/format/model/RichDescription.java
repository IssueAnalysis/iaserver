package com.issue.iaserver.format.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RichDescription {
    private List<DescArea> descAreas;
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

    public void addDescArea(DescArea descArea) {
        this.descAreas.add(descArea);
    }

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    public void appendRichText(String richText) {
        this.richText += richText;
    }

    public void generateRichText() {
        StringBuilder sb = new StringBuilder();
        for (DescArea descArea : descAreas) {
            sb.append(descArea.getContent());
        }
        richText = sb.toString();
    }

    public List<DescArea> getAreasOfType(String type) {
        return descAreas.stream()
                .filter(descArea -> descArea.getType().equals(type))
                .collect(Collectors.toList());
    }
}
