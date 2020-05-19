package com.issue.iaserver.data.lucene;

import com.issue.iaserver.data.mongodb.CSVitem;
import com.issue.iaserver.data.mongodb.DBOperation;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 同步
 * User: 钟镇鸿
 * Date: 2020/5/14
 * Time: 19:00
 * Description:
 */
public class LuceneSyn {

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

    DBOperation dbOperation = new DBOperation();

    //定义生成索引仓库的位置,用户存储
    private static final String path =
            "D:\\lucene_repository";

    public void Synchronization(MongoDatabase database, ArrayList<String> collectionNames){
        ArrayList<CSVitem> list = new ArrayList<>();
        List<Document> documentList = new ArrayList<>();
        try{
            for(String collectionName : collectionNames) {
                MongoCollection<org.bson.Document> collection = database.getCollection(collectionName);
                FindIterable<org.bson.Document> iterable = collection.find();
                //通过迭代器遍历找到的文档中的信息
                MongoCursor<org.bson.Document> iterator = iterable.iterator();
                while (iterator.hasNext()) {
                    org.bson.Document str = iterator.next();
                    Document document = new Document();
                    /**
                     * 往文档里创建域，将表的字段作为域名，并且将值设置进去，保存
                     * TextField是添加索引，需要分词，并且存储
                     */
                    document.add(new TextField("_id", str.getObjectId("_id")+"", Field.Store.YES));
                    document.add(new TextField("id", str.getLong("id")+"", Field.Store.YES));
                    document.add(new TextField("CSV id", str.getLong("CSV id")+"", Field.Store.YES));
                    for(int i=0 ; i<=15 ; i++){
                        document.add(new TextField(FILE_HEADER_MAPPING[i], str.getString(FILE_HEADER_MAPPING[i]), Field.Store.YES));
                    }
                    document.add(new TextField("Intension", str.getString("Intension"), Field.Store.YES));
                    document.add(new TextField("Consideration", str.getString("Consideration"), Field.Store.YES));

                    //content搜索字段
                    document.add(new TextField("content", str.toString(), Field.Store.YES));
                    documentList.add(document);
                }
            }
            //3. 建立分析器（分词器）对象(Analyzer)，用于分词 // 数据分析
            Analyzer analyzer = new StandardAnalyzer();

            //4. 建立索引库配置对象（IndexWriterConfig），配置索引库
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
            //配置打开索引库的方式
            indexWriterConfig.setOpenMode( IndexWriterConfig.OpenMode.CREATE );

            //5. 建立索引库目录对象（Directory），指定索引库的位置
            Directory directory = FSDirectory.open( Paths.get(path));

            //6. 建立索引库操作对象（IndexWriter），操作索引库
            IndexWriter indexWriter = new IndexWriter( directory,indexWriterConfig );

            //7. 使用IndexWriter，把文档对象写入索引库
            indexWriter.addDocuments( documentList );

            //8. commit/关闭释放资源
            indexWriter.close();
        }catch(MongoException | IOException e){
            e.printStackTrace();
        }
    }
}
