package com.chao.bysj.repository;

import com.chao.bysj.po.Order;
import com.chao.bysj.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by chao on 2017/5/4.
 */
public interface OrderRepository extends JpaRepository<Order,String>,JpaSpecificationExecutor<Order>{
    @Query("select o from Order o where o.user = ?1")
    List<Order> findByUser(User user);

    @Query("select o from Order o where o.user = ?1 and o.state=?2")
    List<Order> findByUserAndState(User user,String state);
}
