package com.iaserver.data.service;

import com.iaserver.data.mysql.entity.UserDO;
import org.bson.Document;

import java.util.ArrayList;

/**
 * 操作csv文件接口
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 16:26
 * Description:
 */
public interface OperateFileService {
    void uploadFile(UserDO user, String filePath);

    ArrayList<Document> getFileContent(String url);
}