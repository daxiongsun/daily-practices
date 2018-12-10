package com.ngsky.tools.repository;

import com.ngsky.tools.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <dl>
 * <dt>UserRepository</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 10/30/2018 11:34 PM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = " INSERT INTO user(mpath,mname,mdesc) VALUES (:mpath, :mname, :mdesc)",nativeQuery = true)
    int saveUser(@Param("mpath")String mpath, @Param("mname")String mname, @Param("mdesc")String mdesc);
}
