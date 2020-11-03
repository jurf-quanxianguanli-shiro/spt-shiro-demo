package com.shiro.ljf.demo.sptshirodemo.dao;

import com.shiro.ljf.demo.sptshirodemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    //注册数据
    public void addData(User user);
}
