package com.shiro.ljf.demo.sptshirodemo.service;

import com.shiro.ljf.demo.sptshirodemo.entity.User;

public interface UserService {
    //注册用户方法
    void register(User user);
    //查找用户名
    public User findByUserName(String username);
    //根据用户名查询所有角色
    User findRolesByUserName(String username);
}
