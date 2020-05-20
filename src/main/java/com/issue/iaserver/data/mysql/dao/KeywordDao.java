package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.KeywordDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 关键词
 * User: 钟镇鸿
 * Date: 2020/5/20
 * Time: 19:33
 * Description:
 */
@Repository
public interface KeywordDao extends JpaRepository<KeywordDO, Long> {
}
