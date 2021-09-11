package com.tbyf.demo.springbootdemo.service;

import com.tbyf.demo.springbootdemo.entity.User;

import java.util.List;

public interface UserSevice {

/**
 * 查找全部用户列表
 * @author zhengg
 * @return List<User>
 *
 * **/
    List<User> selectUserList();


    /**
     *
     * 通过id查找
     * @author zhengg
     * @return  User
     * @param id 用户id
     * **/
    User selectUserById(String id);


    /**
     * 通过用户名查找
     * @author zhengg
     * @param  username 用户名
     * @return List<User>
     * **/
    List<User> selectUserByName(String username);

}
