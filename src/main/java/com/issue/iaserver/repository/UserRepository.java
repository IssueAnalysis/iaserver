package com.issue.iaserver.repository;

import com.issue.iaserver.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select * from account where login_name=:loginName")
    Optional<User> getByLoginName(@Param("loginName") String loginName);
}
