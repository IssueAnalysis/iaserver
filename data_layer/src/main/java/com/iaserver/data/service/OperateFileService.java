package com.iaserver.data.service;

import com.iaserver.data.mysql.entity.CSVDO;
import com.iaserver.data.mysql.entity.UserDO;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作csv文件接口
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 16:26
 * Description:
 */
public interface OperateFileService {
    /**
     * 上传文件
     * @param user 用户实体类
     * @param filePath 上传文件的本地地址
     * */
    void uploadFile(UserDO user, String filePath);

    /**通过csvid来获取csv实体类*/
    List<Document> getDocumentByCSVid(long csvId);

    /**获取全部csv文件*/
    List<CSVDO> getAllCSV();

    /**通过用户id获取csv文件，也就是用户上传的csv文件*/
    List<CSVDO> getCSVByUser(long userid);

    /**通过内容获取csv文件*/
    List<CSVDO> getCSVByText(String content);

    /**修改csv文件的intension，consideration*/
    CSVDO updateCSV(CSVDO csvdo);
}