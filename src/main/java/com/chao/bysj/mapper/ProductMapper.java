package com.chao.bysj.mapper;

import com.chao.bysj.po.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chao on 2017/4/12.
 */
@Mapper
@Component
public interface ProductMapper {
    List<Product> selectByName(String productName);
}
