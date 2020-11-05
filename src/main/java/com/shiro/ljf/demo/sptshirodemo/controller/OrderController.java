package com.shiro.ljf.demo.sptshirodemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Security;

/**
 * @ClassName: OrderController
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/11/04 13:06:32 
 * @Version: V1.0
 **/
@Controller
@RequestMapping("order")

public class OrderController {
    @RequiresRoles(value={"admin","guest"})
    @RequiresPermissions(value="haha:add:01")
    @RequestMapping("save")
    public String index(){
        System.out.println("欢迎跳转至订单主页");
        /**
           Subject subject= SecurityUtils.getSubject();
       if(subject.hasRole("admin")){
           System.out.println("有权限访问。。。");
           return "redirect:/order.jsp";
       }
       else{
           System.out.println("无权限访问。。。");
           return "redirect:/login.jsp";
       }
**/
        return "redirect:/order.jsp";
    }
}
