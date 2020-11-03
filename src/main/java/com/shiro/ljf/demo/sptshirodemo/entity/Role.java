package com.shiro.ljf.demo.sptshirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: Role
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/30 22:42:08 
 * @Version: V1.0
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String id;
    private String name;
    //定义权限的集合
    ///private List<Perms> perms;
}

