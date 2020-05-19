package com.issue.iaserver.data.service;

import com.issue.iaserver.data.mongodb.CSVitem;

import java.util.ArrayList;

/**
 * 搜索接口
 * User: 钟镇鸿
 * Date: 2020/5/14
 * Time: 19:13
 * Description:
 */
public interface SearchService {

    /**同步信息到Lucene仓库*/
    void syn();

    ArrayList<CSVitem> search(String content) throws Exception;
}
