package com.issue.iaserver.nlp.nlpUtil;

import com.issue.iaserver.nlp.model.ModelNotFoundException;
import com.issue.iaserver.nlp.model.Models;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.util.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class NameFinder {


    private final Models models;

    private final Logger logger = LoggerFactory.getLogger(NameFinder.class);

    private Map<String, NameFinderME> nameFinderModelMap;

    @Autowired
    public NameFinder(Models models) {
        this.models = models;
        nameFinderModelMap = new HashMap<>();
        try {
            nameFinderModelMap.put("date",new NameFinderME(models.loadDateNameFinder()));
            nameFinderModelMap.put("location",new NameFinderME(models.loadLocationNameFinder()));
            nameFinderModelMap.put("money",new NameFinderME(models.loadMoneyNameFinder()));
            nameFinderModelMap.put("organization",new NameFinderME(models.loadOrganizationNameFinder()));
            nameFinderModelMap.put("percentage",new NameFinderME(models.loadPercentageNameFinder()));
            nameFinderModelMap.put("person",new NameFinderME(models.loadPersonNameFinder()));
            nameFinderModelMap.put("time",new NameFinderME(models.loadTimeNameFinder()));
        } catch (ModelNotFoundException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private synchronized Span[] findNamesByType(String type,String[] tokens){
        NameFinderME nameFinderME = nameFinderModelMap.get(type);
        Span[] res = nameFinderME.find(tokens);
        nameFinderME.clearAdaptiveData();
        return res;
    }

    public Span[] findTimes(String[] tokens){
        return findNamesByType("time",tokens);
    }

    public Span[] findLocations(String[] tokens) {
        return findNamesByType("location", tokens);
    }

    public Span[] findDates(String[] tokens){
        return findNamesByType("date",tokens);
    }

    public Span[] findMoneys(String[] tokens){
        return findNamesByType("money", tokens);
    }

    public Span[] findOrganizations(String[] tokens){
        return findNamesByType("organization", tokens);
    }

    public Span[] findPercentages(String[] tokens) {
        return findNamesByType("percentage", tokens);
    }

    public Span[] findPersons(String[] tokens) {
        return findNamesByType("person", tokens);
    }
}
