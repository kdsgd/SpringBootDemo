package com.tbyf.demo.springbootdemo.service.impl;

import com.tbyf.demo.springbootdemo.dao.UserDao;
import com.tbyf.demo.springbootdemo.entity.User;
import com.tbyf.demo.springbootdemo.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  UserServiceImpl implements UserSevice {


    @Autowired
    private UserDao userDao;


    @Override
    public List<User> selectUserList() {
        return userDao.selectAll();
    }

    @Override
    public User selectUserById(String id) {
        return userDao.selectById(id);
    }

    @Override
    public List selectUserByName(String username) {
        return userDao.selectByName(username);
    }
}
