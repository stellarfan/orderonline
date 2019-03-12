package com.chao.bysj.repository;

import com.chao.bysj.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by chao on 2017/4/29.
 */
public interface AdminRepository extends JpaRepository<Admin,Integer>{
    @Query("select a from Admin a where a.account = ?1")
    Admin findByAccount(String account);
}
