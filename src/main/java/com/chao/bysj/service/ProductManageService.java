package com.chao.bysj.service;

import com.chao.bysj.mapper.ProductMapper;
import com.chao.bysj.po.Menu;
import com.chao.bysj.po.Product;
import com.chao.bysj.repository.MenuRepository;
import com.chao.bysj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by chao on 2017/4/25.
 */
@Service
public class ProductManageService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MenuRepository menuRepository;

    /**
     * 产品管理-查询产品
     * @param model
     * @return
     */
    public String showProducts(Model model){
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList",productList);
        return "/admin/product";
    }

    /**
     * 分页查询
     * @param model
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public String showProductByPage(Model model,int pageNumber,int pageSize){
        PageRequest request = new PageRequest(pageNumber, pageSize, null);
        Page<Product> productList = productRepository.findAll(request);
        model.addAttribute("productList",productList);
        return "/admin/product";
    }
    /**
     * 按关键字查询产品
     * @param model
     * @return
     */
    public String showProductsLike(Model model,String key){
        System.out.println(key);
        List<Product> productList = productMapper.selectByName(key);
        System.out.println(productList);
        model.addAttribute("productList",productList);
        return "products";
    }

    /**
     * 美食管理-跳转添加页面
     * @param model
     * @return
     */
    public String toAddProduct(Model model){
        List<Menu> menuList = menuRepository.findAll();
        model.addAttribute("menuList",menuList);
        return "admin/addProduct";
    }
    /**
     * 美食管理-新增美食
     * @param model
     * @return
     */
    public String addProduct(Model model, Product product){
        productRepository.save(product);
        return showProductByPage(model,0,5);
    }

    /**
     * 美食管理-删除美食
     * @param model
     * @param id
     * @return
     */
    public String delProduct(Model model,Integer id){
        productRepository.delete(id);
        return showProductByPage(model,0,5);
    }

    public String toUpdateProduct(Model model,Integer id){
        Product product = productRepository.findOne(id);
        List<Menu> menuList = menuRepository.findAll();
        model.addAttribute("menuList",menuList);
        model.addAttribute("product",product);
        return "admin/updateProduct";
    }

    public String updateProduct(Model model,Product product){
        productRepository.save(product);
        return showProductByPage(model,0,5);
    }
}
