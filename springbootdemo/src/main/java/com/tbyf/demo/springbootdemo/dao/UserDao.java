package com.tbyf.demo.springbootdemo.dao;

import com.tbyf.demo.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 查询所有用户
     * @author zhengg
     * @return  List<User>
     * **/
    List<User> selectAll();

    /**
     * 根据id查询用户
     * @author zhengg
     * @return User user
     * @param id 用户id
     * **/
    User selectById(String id);

    /**
     * 根据用户名查用户信息
     * @author zhengg
     * @return  List<User>
     * @param username 用户名
     * **/
    List selectByName(String username);

    /**
     *
     *
     * */
    int insertUser (User user);

    /**
     * @author zhengg
     * **/
    int deleteById(String id);

    /**
     * @author zhengg
     * **/
    int deleteByIds(String[] ids);

    /**
     * @author zhengg
     * **/
    int updateUser(User user);
}
