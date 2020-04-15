package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.CollectDO;
import com.issue.iaserver.data.mysql.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * TODO ..
 * User: 钟镇鸿
 * Date: 2020/4/15
 * Time: 19:27
 * Description:
 */
@Repository
public interface CollectDao extends JpaRepository<CollectDO, Long> {
    @Query("select c from CollectDO c where c.user_id=:user_id")
    ArrayList<CollectDO> getCollectByUserId(@Param("user_id") long user_id);
}