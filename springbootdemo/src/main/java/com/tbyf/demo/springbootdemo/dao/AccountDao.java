package com.tbyf.demo.springbootdemo.dao;

import com.tbyf.demo.springbootdemo.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DAO层
 * @author zg
 *
 * **/
@Mapper
public interface AccountDao {

    /**
     * 查询所有
     * @author zg
     * @param
     * @return List<Account>
     * **/
    List<Account> selectall();

    /**
     *  根据id查询
     *  @author zg
     *  @param  id
     *  @return Account
     * **/
    Account selectbyid(int id);

    /**
     *  新增文章
     *  @author zg
     *  @param  account
     *  @return void
     * **/
    void insert(Account account);

    /**
     *删除文章
     *  @author zg
     *  @param  id
     *  @return void
     * **/
    void delete(int id);

    /**
     *修改文章
     *  @author zg
     *  @param  account
     *  @return void
     * **/
    void update(Account account);
}
