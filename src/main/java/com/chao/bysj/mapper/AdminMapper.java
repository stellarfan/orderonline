package com.chao.bysj.mapper;

import com.chao.bysj.po.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by chao on 2017/4/19.
 */
@Mapper
@Component
public interface AdminMapper {
    Admin selectAccount(String account);
    void updatePassword (Admin admin);
}
