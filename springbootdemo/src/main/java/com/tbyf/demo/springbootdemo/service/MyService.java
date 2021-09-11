package com.tbyf.demo.springbootdemo.service;

import com.tbyf.demo.springbootdemo.dao.AccountDao;
import com.tbyf.demo.springbootdemo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private  AccountDao accountDao;

    public List<Account> selectall(){
        return accountDao.selectall();
    }

    public Account selectbyid(int id){
        return accountDao.selectbyid(id);
    }

    public void delete(int id){
        accountDao.delete(id);
    }

    public void update(Account account){
        accountDao.update(account);
    }

    public void insert(Account account){
        accountDao.insert(account);
    }


}
