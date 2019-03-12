package com.chao.bysj.service;

import com.chao.bysj.mapper.MenuMapper;
import com.chao.bysj.mapper.ProductMapper;
import com.chao.bysj.mapper.UserMapper;
import com.chao.bysj.po.*;
import com.chao.bysj.repository.AddressRepository;
import com.chao.bysj.repository.MenuRepository;
import com.chao.bysj.repository.ProductRepository;
import com.chao.bysj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by chao on 2017/3/28.
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    AddressRepository addressRepository;

    /**
     * 查看对应菜单下面的所有产品
     * @param model
     * @param code
     * @return
     */
    public String showProduct(Model model, Integer code){
        List<Product> productList = menuRepository.findByCode(code).getProducts();
        model.addAttribute("productList",productList);
        List<Menu> menuList = menuRepository.findAll();
        model.addAttribute("menuList",menuList);
        return "products";
    }

    /**
     * 查看菜单
     * @param model
     * @return
     */
    public String showMenu(Model model){
        List<Menu> menulist = menuRepository.findAll();
        model.addAttribute("menuList",menulist);
        return "menu";
    }

    public void findAddress(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        List<Address> list = userRepository.findOne(user.getId()).getAddressId();
        model.addAttribute("addressList",list);
    }

    /**
     * 修改密码
     * @param model
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public String updatePassword(Model model, HttpServletRequest request, String account, String oldPassword, String newPassword){
        try{
            User user = userRepository.findByAccount(account);
            if(!user.getPassword().equals(oldPassword)){
                model.addAttribute("errMsg","原始密码错误");
                return "changepwd";
            }else{
                user.setPassword(newPassword);
            }
            userRepository.save(user);
        }catch (Exception e){
        }
        request.getSession().removeAttribute("user");
        return "login";

    }

    /**
     *  展现收货地址
     * @param model
     * @param request
     * @return
     */
    public String showAddress(Model model, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<Address> addressList = userRepository.findOne(user.getId()).getAddressId();
        model.addAttribute("addressList",addressList);
        return "address";
    }

    public String updateAddress(Model model, HttpServletRequest request,Address address){
        addressRepository.save(address);
        return showAddress(model,request);
    }

    /**
     * 新增收货地址
     * @param model
     * @param request
     * @param address
     * @return
     */
    public String addAddress(Model model, HttpServletRequest request,Address address){
        User user = (User)request.getSession().getAttribute("user");
        List<Address> addressList = userRepository.findOne(user.getId()).getAddressId();
        addressList.add(address);
        userRepository.save(user);
        return showAddress(model,request);
    }

    /**
     * 删除收货地址
     * @param model
     * @param request
     * @param id
     * @return
     */
    public String delAddress(Model model, HttpServletRequest request,Integer id){
        addressRepository.delete(id);
        return showAddress(model,request);
    }

}
