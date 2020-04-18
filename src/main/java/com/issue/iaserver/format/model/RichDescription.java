package com.issue.iaserver.format.model;

import java.util.ArrayList;
import java.util.List;


public class RichDescription {
    private  List<DescArea> descAreas;

    public RichDescription() {
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
}
