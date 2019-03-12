package com.chao.bysj.mapper;

import com.chao.bysj.po.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by chao on 2017/4/10.
 */
@Mapper
@Component
public interface MenuMapper{
    List<Menu> selectMenu();
    Menu selectByCode(Integer code);
    Menu selectById(Integer id);
    void addMenu(Menu menu);
    void updateMenu(Menu menu);
    void delMenu(Integer id);
}
