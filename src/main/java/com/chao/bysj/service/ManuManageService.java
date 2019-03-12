package com.chao.bysj.service;

import com.chao.bysj.mapper.MenuMapper;
import com.chao.bysj.mapper.ProductMapper;
import com.chao.bysj.po.Menu;
import com.chao.bysj.po.Product;
import com.chao.bysj.repository.MenuRepository;
import com.chao.bysj.repository.ProductRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by chao on 2017/4/19.
 */
@Service
public class ManuManageService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MenuRepository menuRepository;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ManuManageService.class);

    /**
     * 查询所有菜品
     * @param model
     * @return
     */
    public String showMenu(Model model){
//        List<Menu> menuList =  menuMapper.selectMenu();
        List<Menu> menuList = menuRepository.findAll();
        model.addAttribute("menuList",menuList);
        return "admin/column";
    }

    /**
     *  添加菜品
     * @param model
     * @param menu
     * @return
     */
    public String addMenu(Model model,Menu menu){
//        Menu exitMenu = menuMapper.selectByCode(menu.getCode());
        Menu exitMenu = menuRepository.findByCode(menu.getCode());
        if(exitMenu != null){
            model.addAttribute("errMsg","该数字标识已存在，请输入其他数字");
            return showMenu(model);
        }else{
            try{
//                menuMapper.addMenu(menu);
                menuRepository.save(menu);
            }catch (Exception e){
                logger.error("菜品添加失败",e);
            }
        }
        return showMenu(model);
    }

    /**
     * 更新菜单
     * @param model
     * @param menu
     * @return
     */
    public String updateMenu(Model model, Menu menu){
        Menu exitMenu = menuRepository.findOne(menu.getId());
        if(exitMenu.getCode()!=menu.getCode()){
            if(menuMapper.selectByCode(menu.getCode())!=null){
                model.addAttribute("errMsg","该数字标识已存在，请输入其他数字");
                return showMenu(model);
            }
        }
        menuRepository.save(menu);

        return showMenu(model);
    }

    /**
     * 删除菜品
     * @param model
     * @param id
     * @return
     */
    public String delMenu(Model model, Integer id){
        List<Product> list = menuRepository.findOne(id).getProducts();
        if(list != null && list.size()>0 ){
            model.addAttribute("errMsg","当前菜品下有所属菜单，不能删除");
            return showMenu(model);
        }
        try{
            menuMapper.delMenu(id);
        }catch (Exception e){
            logger.error("菜品删除失败",e);
        }
        return showMenu(model);
    }
}
