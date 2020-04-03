package com.issue.iaserver.nlp.model;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class ModelAspect {

    private static Logger modelLogger = LoggerFactory.getLogger(ModelAspect.class);

    @Pointcut(value = "execution(public * *(..)) && within(com.issue.iaserver.nlp.model.*)")
    private void allPublicPointCut(){}

    @AfterThrowing(value = "allPublicPointCut()", throwing = "ex")
    private void recordException(Exception ex){
        modelLogger.error(ex.getMessage());
    }



}
