package com.shiro.ljf.demo.sptshirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/29 22:57:35 
 * @Version: V1.0
 *
 * @AllArgsConstructor:它是lombok中的注解,作用在类上;使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
 * @Accessors(chain = true)  开启链式编程
 * @NoArgsConstructor：无参构造函数
 *
 *
 **/


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String  id;
    private String username;
    private String password;
    private String salt;

    //定义角色集合
    private List<Role> roles;
}
