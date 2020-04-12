package com.issue.iaserver.data.service;

import com.issue.iaserver.data.mongdb.CSVitem;
import com.issue.iaserver.data.mysql.entity.CSVDO;

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
     * @param user_id 用户实体类
     * @param filePath 上传文件的本地地址
     * */
    void uploadFile(long user_id, String filePath);

    /**通过csvid来获取list item*/
    List<CSVitem> getCSVitemByCSVid(long csvId);

    /**获取全部csv文件*/
    List<CSVDO> getAllCSV();

    /**通过用户id获取csvitem，也就是用户上传的csv文件*/
    List<CSVDO> getCSVByUser(long userid);

    /**修改csv文件的intension，consideration*/
    void updateCSV(long csv_id, CSVitem csVitem);
}