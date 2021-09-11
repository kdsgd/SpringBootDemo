package com.tbyf.demo.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *
 * @author zhengg
 * @date 2020/11/6
 */
@Controller
public class StoreController {
    /**
     * 跳转到商店详情页面
     * **/
    @RequestMapping("/storeIndex")
    public String intoStoreIndex(){
        return "/store/storeIndex";
    }
}
