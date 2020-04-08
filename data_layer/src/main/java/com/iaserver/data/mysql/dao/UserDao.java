package com.iaserver.data.mysql.dao;

import com.iaserver.data.mysql.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<UserDO, Long> {
}
