package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.mysql.entity.Issue2FocusDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * focusDO和issueDO的对应关系
 * User: 钟镇鸿
 * Date: 2020/5/14
 * Time: 19:23
 * Description:
 */
@Repository
public interface Issue2FocusDao extends JpaRepository<Issue2FocusDO, Long> {
}
