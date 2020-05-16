package com.issue.iaserver.extractor.pojos;

import opennlp.tools.util.Span;

public class SpanInCn {
    private final Span span;
    private final String[] tokens;
    private final String typeInCn;

    public SpanInCn(Span span, String[] tokens,String typeInCn) {
        this.span = span;
        this.tokens = tokens;
        this.typeInCn = typeInCn;
    }

    public SpanInCn(Span span, String[] tokens) {
        this.span = span;
        this.tokens = tokens;
        this.typeInCn = null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(20);
        stringBuilder.append(span.toString());
        stringBuilder.append(" ");
        if (typeInCn != null) {
            stringBuilder.append(typeInCn);
        }
        stringBuilder.append(" ");
        for(String str : tokens){
            stringBuilder.append(str).append(" ");
        }
        return stringBuilder.toString();
    }

    public int getStart(){
        return span.getStart();
    }

    public int getEnd(){
        return span.getEnd();
    }

    public String getType(){ return span.getType();}

    public String getTypeInCn() {
        return typeInCn;
    }
}
