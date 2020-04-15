package com.issue.iaserver.format.service;

import com.issue.iaserver.format.model.RichDescription;

public interface Formatter {

    /**
     * 将description进行分块
     * @param description
     * @return  带有分块信息的description
     */
    public RichDescription getRichDescription(String description);


    /**
     * @param description
     * @return  一个简短的description
     */
    public String getBriefDescription(String description);


    /**
     * 处理description中的空格，tab，换行，编码等问题
     * @param description
     * @return
     */
    public String format(String description);




}
