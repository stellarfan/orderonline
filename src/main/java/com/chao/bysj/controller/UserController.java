package com.chao.bysj.controller;

import com.chao.bysj.po.Address;
import com.chao.bysj.po.Order;
import com.chao.bysj.po.Product;
import com.chao.bysj.service.CarManageService;
import com.chao.bysj.service.OrderManageService;
import com.chao.bysj.service.ProductManageService;
import com.chao.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chao on 2017/4/25.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CarManageService carManageService;
    @Autowired
    OrderManageService orderManageService;
    @Autowired
    ProductManageService productManageService;

    @RequestMapping("/showProduct")
    public String products(Model model, Integer code){
       return userService.showProduct(model,code);
    }

    @RequestMapping("/findByKey")
    public String findByKey(Model model,String key){
        return productManageService.showProductsLike(model,key);
    }

    @RequestMapping("/showMenu")
    public String showMenu(Model model){
      return userService.showMenu(model);
    }

    @RequestMapping("/showCar")
    public String showMenu(Model model, HttpServletRequest request){
        return carManageService.showCar(request,model);
    }


    @RequestMapping("/addShopCar")
    public String addAShopCar(Model model, HttpServletRequest request, Product product){
        return carManageService.addItem(product,request,model);
    }

    @RequestMapping("/delShopCar")
    public String delShopCar(Model model, HttpServletRequest request, Integer id){
        return carManageService.deleteItem(id,request,model);
    }

    @RequestMapping("/changeCount")
    public String changeCount(Model model, HttpServletRequest request, String id,String count){
        return carManageService.changeCount(Integer.parseInt(id),Integer.parseInt(count),request,model);
    }
    @RequestMapping("/loadOrder")
    public String loadOrder(Model model, HttpServletRequest request){
        return orderManageService.addOrder(model,request,null);
    }

    @RequestMapping("/changeOrderAddress")
    public String changeOrderAddress(Model model, HttpServletRequest request,Address address,String path, String orderId){
        address.setAddress(path);
        return orderManageService.changeOrderAddress(model,request,address,orderId);
    }

    @RequestMapping("/changeOrder")
    public String changeOrder(Order order, String pay,Model model,HttpServletRequest request){
        return orderManageService.updateOrder(order,pay,model,request);
    }

    @RequestMapping("/payOrder")
    public String payOrder(String id,Model model){
        return orderManageService.pay(id);
    }

    @RequestMapping("/myOrder")
    public String myOrder(Model model,HttpServletRequest request){
        return orderManageService.myOrder(model,request);
    }

    @RequestMapping("/myOrderState")
    public String myOrderState(Model model,HttpServletRequest request,String state){
        return orderManageService.myOrderState(model, request, state);
    }

    @RequestMapping("/delMyOrder")
    public String delMyOrder(Model model,HttpServletRequest request,String id){
        return orderManageService.delMyOrder(model,request,id);
    }

    @RequestMapping("/toChangepwd")
    public String toChangepwd(){
        return "changepwd";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(Model model, HttpServletRequest request, String account, String oldPassword, String newPassword){
        return userService.updatePassword(model,request,account,oldPassword,newPassword);
    }

    @RequestMapping("/showAddress")
    public String showAddress(Model model, HttpServletRequest request){
        return userService.showAddress(model,request);
    }

    @RequestMapping("/updateAddress")
    public String updataAddress(Model model, HttpServletRequest request, String aid,Address address,String path){
        address.setId(Integer.parseInt(aid));
        address.setAddress(path);
        return  userService.updateAddress(model,request,address);
    }

    @RequestMapping("/addAddress")
    public  String addAddress(Model model, HttpServletRequest request,Address address,String path){
        address.setAddress(path);
        return  userService.addAddress(model,request,address);
    }

    @RequestMapping("/delAddress")
    public  String delAddress(Model model, HttpServletRequest request,String id){
        Integer aid = Integer.parseInt(id);
        return  userService.delAddress(model,request,aid);
    }

}
