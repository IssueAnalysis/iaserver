package com.issue.iaserver.extractor.focus.wnl;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WNLConfig {

    @Bean
    public Dictionary dictionary() throws JWNLException {
        return Dictionary.getDefaultResourceInstance();
    }
}
