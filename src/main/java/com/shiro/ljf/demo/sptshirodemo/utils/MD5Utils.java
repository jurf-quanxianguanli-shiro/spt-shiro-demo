package com.shiro.ljf.demo.sptshirodemo.utils;

/**
 * @ClassName: MD5Utils
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/30 21:54:25 
 * @Version: V1.0
 **/
public class MD5Utils {
    /**
    * @author liujianfu
      Md5：
     1.不可逆
     2.相同的内容，经过MD5生成的加密串，结果也是一样的
     3.生成的是一个16进制，32位的字符串。
     注册： md5(password+“salt盐(随机数)”)  存储这个加密后的的密码，同时存储salt盐
     登录: 通过username查询到对应的salt盐，同时和输入明文password， 经过md5(password+salt) ，与之前注册存储到数据库中的密码进行对比
    */
}
