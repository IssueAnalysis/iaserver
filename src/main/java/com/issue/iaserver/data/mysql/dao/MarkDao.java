package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.MarkDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkDao extends JpaRepository<MarkDO, Long> {
}
