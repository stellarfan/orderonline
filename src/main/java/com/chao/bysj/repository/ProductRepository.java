package com.chao.bysj.repository;

import com.chao.bysj.po.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by chao on 2017/4/29.
 */
public interface ProductRepository extends JpaRepository<Product,Integer>{
    @Query("select p from Product p where p.productName like CONCAT('%',:key,'%')")
    List<Product> selectByName(String key);
}
