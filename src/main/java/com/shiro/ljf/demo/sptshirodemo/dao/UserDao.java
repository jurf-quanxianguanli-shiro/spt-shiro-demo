package com.shiro.ljf.demo.sptshirodemo.dao;

import com.shiro.ljf.demo.sptshirodemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    //注册数据
    public void addData(User user);
    //根据用户名查询用户
    public User findByUserName(String userName);
    //根据用户名查询所有角色
    User findRolesByUserName(String username);
}
