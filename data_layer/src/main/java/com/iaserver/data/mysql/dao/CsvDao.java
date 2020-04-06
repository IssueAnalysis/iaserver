package com.iaserver.data.mysql.dao;

import com.iaserver.data.mysql.entity.CsvDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:25
 */
@Repository
public interface CsvDao extends JpaRepository<CsvDO, Long> {
}
