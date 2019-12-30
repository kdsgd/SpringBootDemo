package com.tbyf.demo.springbootdemo.controller;

import com.tbyf.demo.springbootdemo.model.Account;
import com.tbyf.demo.springbootdemo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MyController {

    //dao中声明方法（xml与dao挂钩）   *.xml写sql语句 service中实现sql语句

    @Autowired
    private MyService myService;

    @RequestMapping("/userList")
    public String userList(Model model){
        List<Account> list = myService.selectall();
        model.addAttribute("list",list);
        return "userList";
    }

    @RequestMapping("/insertPage")
    public String insertcon(){
        return "insertPage";
    }


    @RequestMapping("/updatePage")
    public String update(Account account){
        myService.update(account);
        return "updatePage";
    }

    @GetMapping("/delete")
    public String delete(int id){
        myService.delete(id);
        return "userList";
    }

    @RequestMapping("/insert")
    public String insert(Account account){
        myService.insert(account);
        return "redirect:/userList";
    }

}
