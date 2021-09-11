package com.tbyf.demo.springbootdemo.shiro;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Shiro的配置类
 *
 * 关于Configuration的讲解，可参考一下博客：https://www.cnblogs.com/WUXIAOCHANG/p/10877266.html
 * @author zhengg
 *
 */
@Configuration
public class ShiroConfig {


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean shirofilterfactorybean = new ShiroFilterFactoryBean();

        //securityManager

        shirofilterfactorybean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *    常用的过滤器：
         *       anon: 无需认证（登录）可以访问
         *       authc: 必须认证才可以访问
         *       user: 如果使用rememberMe的功能可以直接访问
         *       perms： 该资源必须得到资源权限才可以访问
         *       role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/login", "anon");
        filterMap.put("templates/blog/insertPage.html","perms[user:add]");
        filterMap.put("templates/blog/updatePage.html", "perms[user:update]");
        filterMap.put("/*", "authc");

        shirofilterfactorybean.setLoginUrl("/login");
        shirofilterfactorybean.setUnauthorizedUrl("/noAuth");
        shirofilterfactorybean.setFilterChainDefinitionMap(filterMap);

        return shirofilterfactorybean;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     * 里面主要定义了登录，创建subject，登出等操作
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }




}
