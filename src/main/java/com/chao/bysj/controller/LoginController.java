package com.chao.bysj.controller;

import com.chao.bysj.po.User;
import com.chao.bysj.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by chao on 2017/3/24.
 */
@Controller
public class LoginController{
    @Autowired
    LoginService loginService;

    @RequestMapping("/admin/login")
    public String toLogin(){
        return "admin/login";
    }

    @RequestMapping("/user/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/toSignup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("/userLogin")
    public String userLogin(Model model, HttpServletRequest request, String account,String password){
        return loginService.userLogin(model,request,account,password);
    }

    @RequestMapping("/signup")
    public String signUp(Model model,User user){
        return loginService.signUp(model,user);
    }

    @RequestMapping("/adminLogin")
    public String adminLogin(Model model, HttpServletRequest request, String account, String password){
        return loginService.adminLogin(model,request,account,password);
    }

    @RequestMapping("/adminUpdatePassword")
    public String adminUpdatePassword(Model model, String account, String oldPassword, String newPassword){
        return loginService.updatePassword(model,account,oldPassword,newPassword);
    }


}
