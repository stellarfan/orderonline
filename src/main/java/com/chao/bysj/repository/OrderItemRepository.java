package com.chao.bysj.repository;

import com.chao.bysj.po.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chao on 2017/5/4.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem,String> {
}
