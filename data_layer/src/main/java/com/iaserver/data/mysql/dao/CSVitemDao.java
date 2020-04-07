package com.iaserver.data.mysql.dao;

import com.iaserver.data.mysql.entity.CSVitemDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSVitemDao extends JpaRepository<CSVitemDO, Long> {
}
