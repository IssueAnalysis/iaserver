package com.issue.iaserver.webserver.model;

import java.util.List;

public class FocusIssueMap {
    private List<String> focuses;
    private List<Integer> counts;

    public FocusIssueMap() {
    }

    public FocusIssueMap(List<String> focuses, List<Integer> counts) {
        this.focuses = focuses;
        this.counts = counts;
    }

    public List<String> getFocuses() {
        return focuses;
    }

    public void setFocuses(List<String> focuses) {
        this.focuses = focuses;
    }

    public List<Integer> getCounts() {
        return counts;
    }

    public void setCounts(List<Integer> counts) {
        this.counts = counts;
    }
}
