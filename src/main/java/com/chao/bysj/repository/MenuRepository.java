package com.chao.bysj.repository;

import com.chao.bysj.po.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by chao on 2017/4/29.
 */
public interface MenuRepository extends JpaRepository<Menu,Integer>{
    @Query("select m from Menu m where m.code = ?1")
    Menu findByCode(Integer code);
}
