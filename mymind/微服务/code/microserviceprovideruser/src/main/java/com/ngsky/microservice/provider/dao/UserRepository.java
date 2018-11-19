package com.ngsky.microservice.provider.dao;

import com.ngsky.microservice.provider.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <dl>
 * <dt>UserRepository</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/11/2018 9:59 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
