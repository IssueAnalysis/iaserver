package data.mysql.dao;

import data.mysql.entity.CSVDO;
import data.mysql.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserDao extends JpaRepository<UserDO, Long> {

    @Query("select c from UserDO c where c.name=:name")
    ArrayList<UserDO> getUserByName(@Param("name") String name);
}
