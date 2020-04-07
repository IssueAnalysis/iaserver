package com.iaserver.data.mongdb;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 15:56
 * Description:
 */
public enum Attribute {
    //SPRING("春天"),SUMMER("夏天"),FALL("秋天"),WINTER("冬天");

    Summary("总结");

    private final String name;

    private Attribute(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
