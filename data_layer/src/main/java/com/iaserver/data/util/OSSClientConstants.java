package com.iaserver.data.util;

/**
 * @class:OSSClientConstants
 * @descript:阿里云注册用户基本常量
 */
public class OSSClientConstants {
    //阿里云API的外网域名
    public static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    //阿里云API的密钥Access Key ID
    public static final String ACCESS_KEY_ID = "LTAIkqlGIbs5wyV8";
    //阿里云API的密钥Access Key Secret
    public static final String ACCESS_KEY_SECRET = "Vk7pO6UfHEMrZIlpzKvSm8TYBR5Nuq";
    //阿里云API的bucket名称
    public static final String BACKET_NAME = "iaserver";
    //阿里云API的文件夹名称,每个request一个folder，每次传入一个request新建一个以requestid命名的folder，并把图片传进去
    public static final String REQUEST_FOLDER="";
    //外网访问时的url 后接+requestid+"/"+图片名
    public static final String URL = "https://iaserver.oss-cn-hangzhou.aliyuncs.com/";
}
