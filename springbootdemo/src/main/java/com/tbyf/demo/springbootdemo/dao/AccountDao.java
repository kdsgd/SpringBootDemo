package com.tbyf.demo.springbootdemo.dao;

import com.tbyf.demo.springbootdemo.model.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountDao {
//    查询所有
    List<Account> selectall();

    //根据id查询
    Account selectbyid(int id);

    //增加
    void insert(Account account);

    //删
    void delete(int id);

    //改
    void update(Account account);
}
