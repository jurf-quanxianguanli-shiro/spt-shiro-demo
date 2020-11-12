package com.shiro.ljf.demo.sptshirodemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: MenuPerms
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/11/04 22:36:19 
 * @Version: V1.0
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MenuPerms implements Serializable {
    private String id;
    private String name;
    private String url;
}
