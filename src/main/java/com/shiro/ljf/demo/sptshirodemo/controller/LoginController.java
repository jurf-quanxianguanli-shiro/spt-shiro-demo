package com.shiro.ljf.demo.sptshirodemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: LoginController
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/28 16:49:05 
 * @Version: V1.0
 **/
@Controller
public class LoginController {
    @RequestMapping("index")
    public String index(){
        System.out.println("欢迎跳转至主页");
        return "index";
    }
}
