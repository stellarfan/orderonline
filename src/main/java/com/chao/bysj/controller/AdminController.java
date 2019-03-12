package com.chao.bysj.controller;

import com.chao.bysj.po.Menu;
import com.chao.bysj.po.OrderQuery;
import com.chao.bysj.po.Product;
import com.chao.bysj.po.User;
import com.chao.bysj.service.CarManageService;
import com.chao.bysj.service.ManuManageService;
import com.chao.bysj.service.OrderManageService;
import com.chao.bysj.service.ProductManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by chao on 2017/4/19.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ManuManageService manuManageService;
    @Autowired
    ProductManageService productManageService;
    @Autowired
    OrderManageService orderManageService;


    /**
     * 打开密码修改界面
     * @return
     */
    @RequestMapping("/pass")
    public String toPass(){
        return "admin/pass";
    }

    /**
     * 菜单管理--展现菜单
     * @return
     */
    @RequestMapping("/showMenu")
    public String showMenu(Model model){
        return manuManageService.showMenu(model);
    }

    /**
     * 菜单管理--添加菜品
     * @param model
     * @param menu
     * @return
     */
    @RequestMapping("/addMenu")
    public String addMenu(Model model,String menu, String code){
        Integer intCode = Integer.parseInt(code);
        Menu m = new Menu();
        m.setMenu(menu);
        m.setCode(intCode);
        return manuManageService.addMenu(model,m);
    }

    /**
     * 菜单管理--更新菜单
     * @param model
     * @param menu
     * @return
     */
    @RequestMapping("/updateMenu")
    public String updateMenu(Model model,String id, String menu, String code){
        Integer intCode = Integer.parseInt(code);
        Integer intId = Integer.parseInt(id);
        Menu m = new Menu();
        m.setId(intId);
        m.setMenu(menu);
        m.setCode(intCode);
        return manuManageService.updateMenu(model,m);
    }

    /**
     * 菜单管理--删除菜品
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/deleteMenu")
    public String delectMenu(Model model, Integer id){
        return  manuManageService.delMenu(model,id);
    }

    /**
     * 美食管理-查询美食
     * @param model
     * @return
     */
    @RequestMapping("/showProduct")
    public String showProduct(Model model){
        return productManageService.showProducts(model);
    }

    @RequestMapping("/showProductByPage")
    public String showProductByPage(Model model,String pageNumber){
        if(pageNumber==null ||"".equals(pageNumber)){
            pageNumber="0";
        }
        return productManageService.showProductByPage(model,Integer.parseInt(pageNumber),5);
    }

    /**
     * 美食管理-添加美食
     * @param model
     * @return
     */
    @RequestMapping("/toAddProduct")
    public String toAddProduct(Model model){
        return productManageService.toAddProduct(model);
    }

    @RequestMapping("/addProduct")
    public String addProduct(Model model, Product product, MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "E://毕业设计//源码//orderonline//src//main//resources//static//product//";
        // 解决中文问题，liunx下中文路径，图片显示问题
         fileName = UUID.randomUUID() + suffixName;
         String pathname = filePath + fileName;
        File dest = new File(pathname);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
//            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setPicture("/product/" + fileName);
        return productManageService.addProduct(model,product);
    }

    /**
     * 美食管理-删除美食
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delProduct")
    public String delProduct (Model model,Integer id){
        return productManageService.delProduct(model,id);
    }

    /**
     * 美食管理-跳转修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toUpdateProduct")
    public String toUpdateProduct(Model model,Integer id){
        return productManageService.toUpdateProduct(model,id);
    }

    /**
     * 美食管理-修改美食信息
     * @param model
     * @param product
     * @return
     */
    @RequestMapping("/updateProduct")
    public String updateProduct(Model model,Product product){
        return productManageService.updateProduct(model,product);
    }

    @RequestMapping("/showAllOrder")
    public String showAllOrder(Model model,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return orderManageService.findAllOrder(model);
    }

    @RequestMapping("/findOrderQuery")
    public String findOrderQuery(Model model, String pageNumber,HttpServletRequest request, OrderQuery orderQuery){
        User user = (User)request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        if(pageNumber==null ||"".equals(pageNumber)){
            pageNumber="0";
        }
        return orderManageService.findOrderByPage(model,Integer.parseInt(pageNumber),5,orderQuery);
    }
    @RequestMapping("/delOrder")
    public String delOrder(Model model,String id){
        return orderManageService.delOrder(model,id);
    }



}
