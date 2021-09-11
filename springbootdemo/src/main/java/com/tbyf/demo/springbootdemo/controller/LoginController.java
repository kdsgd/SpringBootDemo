package com.tbyf.demo.springbootdemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController  {

    private  final static Logger logger = LoggerFactory.getLogger(MyController.class);

    /**
     * 登录逻辑
     * @author MSI-NB
     * @date 2021/7/26
     *
     * */

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest request){

        System.out.println("登录用户名为："+username);
        ModelAndView modelAndView = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        try{
            subject.login(usernamePasswordToken);
            //登录成功
            request.setAttribute("code","1");
            request.setAttribute("msg","登录成功");
            return "redirect:/index";
        }catch (UnknownAccountException e){
            //用户名不存在
            request.setAttribute("code","2");
            request.setAttribute("msg","用户名不存在");
        }
        catch (IncorrectCredentialsException e){
            //密码错误
            request.setAttribute("code","3");
            request.setAttribute("msg","密码错误");
        }
        return "login";
    }


    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/noAuth";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/login";
    }

}
