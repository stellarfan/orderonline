package com.chao.bysj.mapper;

import com.chao.bysj.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by chao on 2017/3/24.
 */
@Mapper
@Component
public interface UserMapper {
    User selectById(Integer uid);
    User selectByName(String username);
    void addUser(User user);
}
