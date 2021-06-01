package com.shiro.ljf.demo.sptshirodemo.dao;

import com.shiro.ljf.demo.sptshirodemo.entity.MenuPerms;
import com.shiro.ljf.demo.sptshirodemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    //注册数据
    public void addData(User user);
    //根据用户名查询用户
    public User findByUserName(String userName);
    //根据用户名查询所有角色
    User findRolesByUserName(String username);
    //根据角色id查询权限集合
    List<MenuPerms> findPermsByRoleId(String id);
}
