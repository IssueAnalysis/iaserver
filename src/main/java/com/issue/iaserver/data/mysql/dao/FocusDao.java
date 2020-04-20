package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.FocusDO;
import com.issue.iaserver.data.mysql.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 关注点 focus
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 19:07
 * Description:
 */
@Repository
public interface FocusDao extends JpaRepository<FocusDO, Long> {

}
