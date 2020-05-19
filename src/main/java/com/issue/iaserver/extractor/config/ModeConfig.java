package com.issue.iaserver.extractor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author songjinze
 * @date 2020/5/16 3:26 下午
 */
@Configuration
@Scope("singleton")
public class ModeConfig {

    private boolean isTestMode;

    public ModeConfig() {
        this.isTestMode = false;
    }

    public void enableTestMode(){
        isTestMode = true;
    }

    public boolean isTestMode(){
        return this.isTestMode;
    }
}
