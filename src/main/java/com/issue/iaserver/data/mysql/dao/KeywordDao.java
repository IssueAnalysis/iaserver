package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.KeywordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关键词
 * User: 钟镇鸿
 * Date: 2020/5/20
 * Time: 19:33
 * Description:
 */
@Repository
public interface KeywordDao extends JpaRepository<KeywordDO, Long> {

    @Query("select c from KeywordDO c where c.csv_id=:csv_id and c.issue_id=:issue_id")
    List<KeywordDO> getKeywordByIssueId(@Param("csv_id")long csv_id, @Param("issue_id")long issue_id);
}