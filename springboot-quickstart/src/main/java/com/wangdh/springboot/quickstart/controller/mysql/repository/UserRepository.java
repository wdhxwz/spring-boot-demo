package com.wangdh.springboot.quickstart.controller.mysql.repository;

import com.wangdh.springboot.quickstart.controller.mysql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wangdh
 * @Description
 * @date 2018-01-26
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
