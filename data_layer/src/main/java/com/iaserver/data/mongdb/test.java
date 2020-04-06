package com.iaserver.data.mongdb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.bson.Document;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 14:41
 * Description:
 */
public class test {
    public static void main(String[] args) {
        DBOperation dbOperation = new DBOperation();
        MongoDBConnection mongoDBConnection = new MongoDBConnection();
        //新建链接
        MongoClient mongoClient = mongoDBConnection.getConn();
        //新建数据库对象
        MongoDatabase mongoDatabase = mongoClient.getDatabase("iadb");
        //查询指定数据库中的所有集合
        System.out.println("原始集合：");
        dbOperation.selectCollection(mongoDatabase);
        //创建新集合
        /*dbOperation.createCollection(mongoDatabase);
        System.out.println("新建集合后，再次查询数据库中的所有集合：");
        dbOperation.selectCollection(mongoDatabase);*/
        //删除集合
        /*dbOperation.deleteCollection(mongoDatabase);
        System.out.println("删除集合后，再次查询数据库中的所有集合：");
        dbOperation.selectCollection(mongoDatabase);*/


        //查询指定集合中的所有数据
        /*System.out.println("原始数据：");
        dbOperation.selectData(mongoDatabase);*/

        //向指定集合中插入数据
        /*dbOperation.insertData(mongoDatabase,"https://iaserver.oss-cn-hangzhou.aliyuncs.com/my_csv_simple.csv");
        System.out.println("插入数据后：");
        dbOperation.selectData(mongoDatabase);*/

        //根据条件修改集合中的数据
        /*dbOperation.updateData(mongoDatabase);
        System.out.println("修改数据后：");
        dbOperation.selectData(mongoDatabase);*/

        //根据条件删除集合中的数据
        /*dbOperation.deleteData(mongoDatabase);
        System.out.println("删除数据后：");
        dbOperation.selectData(mongoDatabase);*/


        //这是将文件插入mongdb的实例
        String name = dbOperation.insertData(mongoDatabase,"https://iaserver.oss-cn-hangzhou.aliyuncs.com/my_csv_simple.csv");
        System.out.println("插入数据后：");
        dbOperation.selectData(mongoDatabase, name);

        //关闭连接
        mongoClient.close();
    }

    //private static final String csvFileName = "my_csv_simple.csv";

    /**
     * Summary
     - Issue key
     - Issue id
     - Parent id
     - Issue Type
     - Status
     - Project key
     - Project name
     - Project type
     - Project lead
     - Project description
     - Project url
     - Priority
     - Resolution
     - description

     - intension(更新)
     - consideration（更新）
     - comment（更新）*/
    // CSV file header
    private static final String[] FILE_HEADER_MAPPING = {
            "Summary",
            "Issue key",
            "Issue id",
            "Parent id",
            "Issue Type",
            "Status",
            "Project key",
            "Project name",
            "Project type",
            "Project lead",
            "Project description",
            "Project url",
            "Priority",
            "Resolution",
            "Description"
    };
}
