package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.mysql.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关注点 focus
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 19:07
 * Description:
 */
@Repository
public interface FocusDao extends JpaRepository<FocusDO, Long> {

    @Query("select c from FocusDO c where c.csv_id=:csv_id and c.issue_id=:issue_id")
    List<FocusDO> getFocusByIssueId(@Param("csv_id")long csv_id, @Param("issue_id")long issue_id);
}
