package com.iaserver.data.service;

import com.iaserver.data.util.AliyunOSSClientUtil;
import com.iaserver.data.util.OSSClientConstants;

/**
 * 操作csv文件接口
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 16:26
 * Description:
 */
public interface OperateFile {
    public void uploadFile(String filePath);
}
