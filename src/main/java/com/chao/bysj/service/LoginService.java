package com.chao.bysj.service;

import com.chao.bysj.po.Admin;
import com.chao.bysj.po.Item;
import com.chao.bysj.po.User;
import com.chao.bysj.repository.AdminRepository;
import com.chao.bysj.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chao on 2017/4/19.
 */
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginService.class);

    /**
     * 管理员登录
     * @param model
     * @param request
     * @param account
     * @param password
     * @return
     */
    public String adminLogin(Model model, HttpServletRequest request, String account, String password){
        Admin admin = adminRepository.findByAccount(account);
//        Admin admin =  adminMapper.selectAccount(account);
        if(admin == null ){
            model.addAttribute("errMsg", "账号不存在!");
            return "admin/login";
        }else if(!password.equals(admin.getPassword())){
            model.addAttribute("errMsg", "密码不正确!");
            return "admin/login";
        }else{
            request.getSession().setAttribute("admin",admin);
            return "admin/index";
        }
    }

    /**
     * 管理员修改密码
     * @param model
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public String updatePassword(Model model,String account,String oldPassword, String newPassword){
        try{
            Admin oldAdmin = adminRepository.findByAccount(account);
            if(!oldAdmin.getPassword().equals(oldPassword)){
                model.addAttribute("errMsg","原始密码错误");
                return "admin/pass";
            }else{
                oldAdmin.setPassword(newPassword);
            }
//            adminMapper.updatePassword(oldAdmin);
            adminRepository.save(oldAdmin);
        }catch (Exception e){
            logger.error("修改密码错误",e);
        }
        return "admin/login";

    }

    /**
     * 用户登录
     * @param model
     * @param request
     * @param account
     * @param password
     * @return
     */
    public String userLogin(Model model, HttpServletRequest request, String account,String password){
        User user = userRepository.findByAccount(account);
        if(user == null ){
            model.addAttribute("errMsg", "账号不存在!");
            return "login";
        }else if(!password.equals(user.getPassword())){
            model.addAttribute("errMsg", "密码不正确!");
            return "login";
        }else{
            request.getSession().setAttribute("user",user);
            Map<Integer, Item> shopCar = new LinkedHashMap<>();
            request.getSession().setAttribute("shopCar",shopCar);
            return "index";
        }
    }

    /**
     * 用户注册
     * @param model
     * @param user
     * @return
     */
    public String signUp(Model model,User user){
        User exUser = userRepository.findByAccount(user.getAccount());
        if(exUser!=null){
            model.addAttribute("errMsg","账号已存在");
            return "signup";
        }else{
            userRepository.save(user);
            model.addAttribute("succeed","注册成功，请登录");
            return "login";
        }
    }
}
