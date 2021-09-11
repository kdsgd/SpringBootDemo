package com.tbyf.demo.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.tbyf.demo.springbootdemo.entity.Account;
import com.tbyf.demo.springbootdemo.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MyController {

    private  final static Logger logger = LoggerFactory.getLogger(MyController.class);

    //dao中声明方法（xml与dao挂钩）   *.xml写sql语句 service中实现sql语句

    @Autowired
    private MyService myService;

    /**
     * 访问权限控制
     *
     * @author zhengg
     * **/
//    @GetMapping()
//    public String toLogin(HttpServletRequest request)
//    {
//
//        HttpSession session = request.getSession();
//        String username =(String) session.getAttribute("username");
//        logger.info("获取到session中的username值:{}",username);
//        logger.info("获取到的session为:{}",session);
//        if (username == null){
//            return "login";
//        }else {
//            return  "index";
//        }
//    }


    /**
     * index页面路由跳转
     */
    @RequestMapping("/index")
    public String toIndex(){
        logger.info("现在跳转到页面index.html");
        return "index";
    }



    @RequestMapping("/main")
    public String main(){
        System.out.println("return到main");
        return "main";
    }

    /**
     * insertPage页面路由跳转
     */
    @RequestMapping("/insertPage")
    public String insertcon(){
        System.out.println("进入添加页面!");
        return "/blog/insertPage";
    }


    /**
     * 编辑页面跳转
     */
    @RequestMapping("/update")
    public String update(Account account,int id,Model model){
        Account selectbyid = myService.selectbyid(id);
        model.addAttribute("selectbyid",selectbyid);
        myService.update(account);
//        return "/blog/updatePage";
        return "redirect:/blog";
    }

    /**
     * @author zhengg
     * 编辑页面保存后页面重定向到index
     */
    @RequestMapping("/updatePage")
    public String updateCorn(Account account,int id,Model model){
        Account selectbyid = myService.selectbyid(id);
        model.addAttribute("selectbyid",selectbyid);
        myService.update(account);
//        return "redirect:/blog";
        return "/blog/updatePage";
    }

    /**
     * 删除页面跳转
     */
    @GetMapping("/delete")
    public String delete(int id){
        myService.delete(id);
        return "/blog/blog";
    }

    /**
     * 新增页面保存后页面重定向到index
     */
    @RequestMapping("/insert")
    public String insert(Account account){
        myService.insert(account);
        System.out.println("添加成功!");
        return "redirect:/blog";
    }

    /**
     * blog页面路由重定向
     */
    @RequestMapping("/blog")
    public  String intoBlog(Model model){
        System.out.println("文章页面跳转成功");
        List<Account> list = myService.selectall();
        model.addAttribute("list",list);
        return "/blog/blog";
    }


    /**
     * 资讯页面
     * **/
    @RequestMapping("/newslist")
    public  String intoNews(){
        return "newslist";
    }

    /**
     * 跳转到论坛页面
     * **/
    @RequestMapping("/social_feed")
    public String intoSocial(){
        return "social_feed";
    }


    /**
     * 跳转到资讯详情页面
     * **/
    @RequestMapping("/article")
    public String intoArticle(){
        return "article";

    }



    @RequestMapping("/theEcharts")
    @ResponseBody
    public JSONObject toEcharts (){
        JSONObject json = new JSONObject();
        Object obj = myService.selectall();
        json.put("ecData",obj);
        return json;
    }

    @RequestMapping("/Echarts")
    public String toTheEcharts(){
        return "Echarts";
    }

    @RequestMapping("/pages")
    public String pages(){
        return "pages";
    }

    @RequestMapping("/pay")
    public String WxPay(){
        return "pay";
    }

    /**
     * 跳转到恶搞页面
     * @author zhengg
     * **/
    @RequestMapping("/long")
    public String into76(){
        return "long";

    }

    /**
     * 跳转到错误页面
     * **/
    @RequestMapping("/404")
    public String intoError(){
        return "/error/404";
    }
}

