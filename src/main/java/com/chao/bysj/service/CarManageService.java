package com.chao.bysj.service;

import com.chao.bysj.po.Item;
import com.chao.bysj.po.Product;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * Created by chao on 2017/5/2.
 */
@Service
public class CarManageService {
    /**
     * 购物车页面
     * @param request
     * @param model
     * @return
     */
    public String showCar(HttpServletRequest request,Model model){
        LinkedHashMap<Integer,Item> shopCar = ( LinkedHashMap<Integer,Item>)request.getSession().getAttribute("shopCar");
        // 如果未登录，跳转登录界面
        if(shopCar == null){
            return "login";
        }
        BigDecimal total = getTotal(shopCar);
        model.addAttribute("shopCar",shopCar);
        model.addAttribute("total",total);
        return "car";
    }

    /**
     * 添加美食到购物车
     * @param product
     * @param request
     * @param model
     * @return
     */
    public String addItem(Product product,HttpServletRequest request,Model model){
        Integer count = 1;
        LinkedHashMap<Integer,Item> shopCar = ( LinkedHashMap<Integer,Item>)request.getSession().getAttribute("shopCar");
        if(shopCar == null){
            return "login";
        }
        //如果已存在，数量加1
        if(shopCar.containsKey(product.getId())){
            count++;
            Item item = new Item(count,product);
            shopCar.put(product.getId(),item);
        }else{
            Item item = new Item(count,product);
            shopCar.put(product.getId(),item);

        }
       BigDecimal total = getTotal(shopCar);
        model.addAttribute("shopCar",shopCar);
        model.addAttribute("total",total);
        return "car";

    }

    /**
     * 删除购物车中的美食
     * @param id
     * @param request
     * @param model
     * @return
     */
    public String deleteItem(Integer id,HttpServletRequest request,Model model){
        LinkedHashMap<Integer,Item> shopCar = ( LinkedHashMap<Integer,Item>)request.getSession().getAttribute("shopCar");
        shopCar.remove(id);
        BigDecimal total = getTotal(shopCar);
        model.addAttribute("shopCar",shopCar);
        model.addAttribute("total",total);
        return "car";
    }

    /**
     * 改变购物车的美食数量
     * @param id
     * @param count
     * @param request
     * @param model
     * @return
     */
    public String changeCount(Integer id,Integer count,HttpServletRequest request,Model model){
        LinkedHashMap<Integer,Item> shopCar = ( LinkedHashMap<Integer,Item>)request.getSession().getAttribute("shopCar");
        shopCar.get(id).setCount(count);
        BigDecimal total = getTotal(shopCar);
        model.addAttribute("shopCar",shopCar);
        model.addAttribute("total",total);
        return "car";
    }

    /**
     * 计算总价
     * @param shopCar
     * @return
     */
    public static BigDecimal getTotal(LinkedHashMap<Integer,Item> shopCar){
        BigDecimal total = new BigDecimal("0");
        for(Item i: shopCar.values()){
            total = total.add(i.getSubtotal());
        }
        return total;

    }
}
