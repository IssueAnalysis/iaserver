package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.CSVDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CSVDao extends JpaRepository<CSVDO, Long> {

    @Query("select c from CSVDO c where c.userId=:user_id")
    ArrayList<CSVDO> getCSVByUserId(@Param("user_id") long user_id);
}
