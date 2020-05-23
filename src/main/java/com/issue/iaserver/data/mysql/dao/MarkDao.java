package com.issue.iaserver.data.mysql.dao;

import com.issue.iaserver.data.mysql.entity.MarkDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MarkDao extends JpaRepository<MarkDO, Long> {

    @Query("select m from MarkDO m where m.csv_id=:csv_id and m.item_id=:item_id")
    ArrayList<MarkDO> getMarkById(@Param("csv_id") long csv_id, @Param("item_id") long item_id);
}
