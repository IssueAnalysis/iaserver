package com.issue.iaserver.data.lucene;


import com.issue.iaserver.data.mongodb.CSVitem;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * 检索：就是我们用户输入的信息，我们进行模糊查询，最终将数据返回给用户
 * User: 钟镇鸿
 * Date: 2020/5/14
 * Time: 19:29
 * Description:
 */
public class LuceneSearch {

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

    //定义生成索引仓库的位置，用户获取文件中的数据
    private static final String path =
            "D:\\lucene_repository";

    public ArrayList<CSVitem> search(String content) throws Exception{
        ArrayList<CSVitem> res = new ArrayList<CSVitem>();

        //1. 建立分析器对象（Analyzer），用于分词，用什么创建的，就用对应的分析
        Analyzer analyzer = new StandardAnalyzer();

        //2. 建立查询对象（Query）,需要先创建查询的解析器
        /**
         * 查询解析器，需要指定我们查询的是哪个域，并且需要给分析器
         */
        QueryParser queryParser = new QueryParser( "content",analyzer );
        //开始解析，解析的格式是 ==> 域名：需要搜索的信息
        Query query = queryParser.parse( "content:" + content);

        //3. 建立索引库目录对象（Directory），指定索引库的位置
        Directory directory = FSDirectory.open( Paths.get(path));

        //4. 建立索引读取对象（IndexReader），把索引数据读取到内存中
        //使用的是 目录的读取
        IndexReader indexReader = DirectoryReader.open( directory );

        //5. 建立索引搜索对象（IndexSearcher），执行搜索，返回搜索的结果集（TopDocs）
        IndexSearcher indexSearcher = new IndexSearcher( indexReader );

        /**
         * 6. 处理结果集
         * 查询需要连接查询的条件，并且给出查询多少条
         */
        TopDocs topDocs = indexSearcher.search( query, 1000 );

        //TOPDocs就是获取的所有的条数，基本上是获取了所有的数据的条数
        System.out.println("获取到符合条件的条数为："+topDocs.totalHits);

        //获取符合条件的数据
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        //遍历输出数据
        for (ScoreDoc scoreDoc : scoreDocs) {

            //根据索引查询文档，文档在根据域名获取到对应的数据
            Document doc = indexSearcher.doc( scoreDoc.doc );

            //System.out.println("content ==> " + doc.get("content"));
            long id = Long.parseLong(doc.get("id"));
            long csv_id = Long.parseLong(doc.get("CSV id"));

            CSVitem item = new CSVitem(
                    id, csv_id,
                    doc.get(FILE_HEADER_MAPPING[0]), doc.get(FILE_HEADER_MAPPING[1]),
                    doc.get(FILE_HEADER_MAPPING[2]),doc.get(FILE_HEADER_MAPPING[3]),
                    doc.get(FILE_HEADER_MAPPING[4]),doc.get(FILE_HEADER_MAPPING[5]),
                    doc.get(FILE_HEADER_MAPPING[6]),doc.get(FILE_HEADER_MAPPING[7]),
                    doc.get(FILE_HEADER_MAPPING[8]),doc.get(FILE_HEADER_MAPPING[9]),
                    doc.get(FILE_HEADER_MAPPING[10]),doc.get(FILE_HEADER_MAPPING[11]),
                    doc.get(FILE_HEADER_MAPPING[12]),doc.get(FILE_HEADER_MAPPING[13]),
                    doc.get(FILE_HEADER_MAPPING[14]),doc.get(FILE_HEADER_MAPPING[15]),
                    doc.get("Intension"),doc.get("Consideration"));
            res.add(item);

            //System.out.println("  =================================================  ");
        }

        //7. 释放资源
        indexReader.close();

        return res;
    }
}
