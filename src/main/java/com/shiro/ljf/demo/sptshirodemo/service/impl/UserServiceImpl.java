package com.shiro.ljf.demo.sptshirodemo.service.impl;

import com.shiro.ljf.demo.sptshirodemo.dao.UserDao;
import com.shiro.ljf.demo.sptshirodemo.entity.MenuPerms;
import com.shiro.ljf.demo.sptshirodemo.entity.User;
import com.shiro.ljf.demo.sptshirodemo.service.UserService;
import com.shiro.ljf.demo.sptshirodemo.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/11/02 18:09:11 
 * @Version: V1.0
 **/
@Service("userService")
public class UserServiceImpl  implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }
    @Override
    public List<MenuPerms> findPermsByRoleId(String id) {
        return userDao.findPermsByRoleId(id);
    }
    /**
     * @author liujianfu
     *根据用户名查询所有角色
     * @date 2020/11/7 20:18
     * @param
     * @return
     @Override
     **/
    public User findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }
    /*
    * @author liujianfu
    * 注册用户
    * @date 2020/11/3 11:53
    * @param [user]
    * @return void
    */
    @Override
    public void register(User user) {
            //处理业务调用dao
            //1.生成随机盐
            String salt = SaltUtils.getSalt(8);
            //2.将随机盐保存到数据
            user.setSalt(salt);
            //3.明文密码进行md5 + salt + hash散列
            Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
            user.setPassword(md5Hash.toHex());
             userDao.addData(user);

    }
}
