package mysql.dao;

import mysql.entity.CsvDO;
import org.springframework.data.repository.CrudRepository;

/**
 * TODO...
 *
 * @author 钟镇鸿
 * @since 2020/3/21 0:25
 */
public interface CsvDao extends CrudRepository<CsvDO, Long> {
}