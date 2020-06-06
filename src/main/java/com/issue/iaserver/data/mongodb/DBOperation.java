package com.issue.iaserver.data.mongodb;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * mongodb操作类
 * User: 钟镇鸿
 * Date: 2020/4/6
 * Time: 14:36
 * Description:
 */
public class DBOperation {
    private final String[] FILE_HEADER_MAPPING = {
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
            "Description",
            "Comment"
    };

    /**
     * 操作数据库集合：
     * 查询、新建、删除
     * @param database
     */
    //查询指定数据库中所有集合
    public ArrayList<Document> selectCollection(MongoDatabase database){
        ArrayList<Document> csVitemDOS = new ArrayList<Document>();
        try{
            //查询Test数据库中所有集合名称
            MongoIterable<String> colNameList = database.listCollectionNames();
            for(String colName: colNameList)
                System.out.println(colName);
            System.out.println("[INFO] : Select collection success！");
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Select collection field！");
        }
        return csVitemDOS;
    }


    //新建集合
    public void createCollection(MongoDatabase database, String collectionName){
        try{
            //创建新集合
            database.createCollection(collectionName);
            System.out.println("[INFO] : Create collection success！");
        } catch(MongoCommandException e){
            e.printStackTrace();
            System.out.println("a collection already exists，请勿重复操作");
        } catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Create collection field！");
        }
    }


    //删除集合
    public void deleteCollection(MongoDatabase database, String collectionName){
        try{
            //删除集合
            MongoCollection mongoCollection = database.getCollection(collectionName);
            mongoCollection.drop();
            System.out.println("[INFO] : Drop collection success！");
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Drop collection field！");
        }
    }


    /**
     * 查询单个collection的数据
     * */
    public ArrayList<CSVitem> selectData(MongoDatabase database, String collectionName){
        ArrayList<CSVitem> list = new ArrayList<>();
        try{
            //获取数据库中的user集合
            MongoCollection<Document> collection = database.getCollection(collectionName);
            //获取user集合中的文档
            FindIterable<Document> iterable = collection.find();
            //通过迭代器遍历找到的文档中的信息
            MongoCursor<Document> iterator = iterable.iterator();
            while(iterator.hasNext()) {
                Document str = iterator.next();
                CSVitem item = new CSVitem(
                        0, Long.valueOf(collectionName),
                        str.getString(FILE_HEADER_MAPPING[0]), str.getString(FILE_HEADER_MAPPING[1]),
                        str.getString(FILE_HEADER_MAPPING[2]),str.getString(FILE_HEADER_MAPPING[3]),
                        str.getString(FILE_HEADER_MAPPING[4]),str.getString(FILE_HEADER_MAPPING[5]),
                        str.getString(FILE_HEADER_MAPPING[6]),str.getString(FILE_HEADER_MAPPING[7]),
                        str.getString(FILE_HEADER_MAPPING[8]),str.getString(FILE_HEADER_MAPPING[9]),
                        str.getString(FILE_HEADER_MAPPING[10]),str.getString(FILE_HEADER_MAPPING[11]),
                        str.getString(FILE_HEADER_MAPPING[12]),str.getString(FILE_HEADER_MAPPING[13]),
                        str.getString(FILE_HEADER_MAPPING[14]),str.getString(FILE_HEADER_MAPPING[15]),
                        str.getString("Intension"),str.getString("Consideration"));
                list.add(item);
            }
            System.out.println("[INFO] : Select com.issue.iaserver.data success！");
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Select com.issue.iaserver.data field！");
        }
        return list;
    }

    /**
     * 通过csvid和itemid查询单个CSVitem的数据
     * */
    public CSVitem getItem(MongoDatabase database, long csv_id, long item_id){
        ArrayList<CSVitem> list = new ArrayList<>();
        try{
            //获取数据库中的user集合
            MongoCollection<Document> collection = database.getCollection("0");
            //获取user集合中的文档
            FindIterable<Document> iterable = collection.find();
            //通过迭代器遍历找到的文档中的信息
            MongoCursor<Document> iterator = iterable.iterator();
            while(iterator.hasNext()) {
                Document str = iterator.next();
                if(str.getLong("CSV id") == csv_id && str.getLong("id") == item_id){
                    CSVitem item = new CSVitem(
                            item_id, csv_id,
                            str.getString(FILE_HEADER_MAPPING[0]), str.getString(FILE_HEADER_MAPPING[1]),
                            str.getString(FILE_HEADER_MAPPING[2]),str.getString(FILE_HEADER_MAPPING[3]),
                            str.getString(FILE_HEADER_MAPPING[4]),str.getString(FILE_HEADER_MAPPING[5]),
                            str.getString(FILE_HEADER_MAPPING[6]),str.getString(FILE_HEADER_MAPPING[7]),
                            str.getString(FILE_HEADER_MAPPING[8]),str.getString(FILE_HEADER_MAPPING[9]),
                            str.getString(FILE_HEADER_MAPPING[10]),str.getString(FILE_HEADER_MAPPING[11]),
                            str.getString(FILE_HEADER_MAPPING[12]),str.getString(FILE_HEADER_MAPPING[13]),
                            str.getString(FILE_HEADER_MAPPING[14]),str.getString(FILE_HEADER_MAPPING[15]),
                            str.getString("Intension"),str.getString("Consideration"));
                    return item;
                }
            }
            System.out.println("[INFO] : Get CSV item success！");
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Get CSV item field！");
        }
        return null;
    }


    /**存储数据*/
    public String insertData(MongoDatabase database, long csvId, String url){
        long id = 0;

        InputStreamReader isReader = null;
        CSVParser csvFileParser = null;

        MongoClient mongoClient = null;

        String collectionName = csvId+"";
        try {
            //新建集合
            createCollection(database, collectionName);
            // ---------- Creating Collection -------------------------//
            MongoCollection<Document> table = database.getCollection(collectionName);
            MongoCollection<Document> table0  = database.getCollection("0");

            // Create the CSVFormat object with the header mapping
            CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

            // Create a new list of student to be filled by CSV file com.issue.iaserver.data
            List<CSVitem> csVitems = new ArrayList<CSVitem>();

            URL u =new URL(url); // 创建URL
            URLConnection urlconn = u.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn =(HttpURLConnection)urlconn;

            //int filesize = urlconn.getContentLength(); // 取数据长度
            isReader = new InputStreamReader(urlconn.getInputStream(),"UTF-8");

            // initialize CSVParser object
            csvFileParser = new CSVParser(isReader, csvFileFormat);

            //Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(isReader);
            // Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            // Read the CSV file records starting from the second record to skip the header
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                // Create a new student object and fill his com.issue.iaserver.data
                //System.out.println( record);
                CSVitem item = new CSVitem(
                        id++,
                        csvId,
                        record.get("Summary"),
                        record.get("Issue key"),
                        record.get("Issue id"),
                        record.get("Parent id"),
                        record.get("Issue Type"),
                        record.get("Status"),
                        record.get("Project key"),
                        record.get("Project name"),
                        record.get("Project type"),
                        record.get("Project lead"),
                        record.get("Project description"),
                        record.get("Project url"),
                        record.get("Priority"),
                        record.get("Resolution"),
                        record.get(41)+record.get(42)+record.get(43)+record.get(44)+record.get(45),
                        record.get(265)+record.get(266)+record.get(267)+record.get(268)+record.get(269),
                        "",
                        ""
                );

                csVitems.add(item);
            }

            // Print the new csv list
            for (CSVitem item : csVitems) {
                // ---------- Creating Document ---------------------------//
                Document doc = new Document();
                doc.append("id", item.getId());
                doc.append("CSV id", item.getCSVid());
                doc.append("Summary", item.getSummary());
                doc.append("Issue key", item.getIssue_key());
                doc.append("Issue id", item.getIssue_id());
                doc.append("Parent id", item.getParent_id());
                doc.append("Issue Type", item.getIssue_type());
                doc.append("Status", item.getStatus());
                doc.append("Project key", item.getProject_key());
                doc.append("Project name", item.getProject_name());
                doc.append("Project type", item.getProject_type());
                doc.append("Project lead", item.getProject_lead());
                doc.append("Project description", item.getProject_description());
                doc.append("Project url", item.getProject_url());
                doc.append("Priority", item.getPriority());
                doc.append("Resolution", item.getResolution());
                doc.append("Description", item.getDescription());
                doc.append("Comment", item.getComment());
                doc.append("Intension", item.getIntension());
                doc.append("Consideration", item.getConsideration());

                // ----------- Inserting Data ------------------------------//
                table.insertOne(doc);
                table0.insertOne(doc);

                System.out.println(item.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                isReader.close();
                csvFileParser.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader/csvFileParser !!!");
                e.printStackTrace();
            }
        }
        return collectionName;
    }

    //修改数据，只允许修改intension、consideration
    public void updateData(MongoDatabase database, long csvid, long csvitem_id, String intension, String consideration){
        try {
            MongoCollection mongoCollection = database.getCollection(csvid+"");
            //修改满足条件的第一条数据
            mongoCollection.updateOne(Filters.eq("id",csvitem_id),
                    new Document("$set",new Document("Intension",intension)));
            mongoCollection.updateOne(Filters.eq("id",csvitem_id),
                    new Document("$set",new Document("Consideration",consideration)));
            //System.out.println("ok");
            //修改满足条件的所有数据
            //mongoCollection.updateMany(Filters.eq("user_name","test"),new Document("$set",new Document("user_pwd","tttt")));
            System.out.println("[INFO] : Update com.issue.iaserver.data success！");
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Update com.issue.iaserver.data field！");
        }

    }

    //删除数据
    public void deleteData(MongoDatabase database){
        try {
            MongoCollection mongoCollection = database.getCollection("user");
            //删除满足条件的第一条记录
            mongoCollection.deleteOne(Filters.eq("user_name","test"));
            //删除满足条件的所有数据
            mongoCollection.deleteMany(Filters.eq("user_name","test"));
            System.out.println("[INFO] : Delete com.issue.iaserver.data success！");
        }catch(MongoException e){
            e.printStackTrace();
            System.out.println("[ERROR] : Delete com.issue.iaserver.data field！");
        }
    }
}