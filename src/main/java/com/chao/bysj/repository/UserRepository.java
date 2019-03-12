package com.chao.bysj.repository;

import com.chao.bysj.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by chao on 2017/4/28.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.account = ?1")
    User findByAccount(String account);
}
