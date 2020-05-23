package com.issue.iaserver.format.service;

import com.issue.iaserver.format.model.RichDescription;

public interface Formatter {

    /**
     * 将description进行分块
     * @param description
     * @return  带有分块信息的description
     */
    RichDescription getRichDescription(String description);


    /**
     * @param description
     * @return  一个简短的description
     */
    String getBriefDescription(String description);


    /**
     * 处理description中的空格，tab，换行，编码等问题
     * @param description
     * @return
     */
    String format(String description);




}
