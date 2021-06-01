package com.shiro.ljf.demo.sptshirodemo.service;

import com.shiro.ljf.demo.sptshirodemo.entity.MenuPerms;
import com.shiro.ljf.demo.sptshirodemo.entity.User;

import java.util.List;

public interface UserService {
    //注册用户方法
    void register(User user);
    //查找用户名
    public User findByUserName(String username);
    //根据用户名查询所有角色
    User findRolesByUserName(String username);

    //根据角色id查询权限集合
    List<MenuPerms> findPermsByRoleId(String id);
}
