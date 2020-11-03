package com.shiro.ljf.demo.sptshirodemo.controller;

import com.shiro.ljf.demo.sptshirodemo.entity.User;
import com.shiro.ljf.demo.sptshirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: UserLoginController
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/29 08:13:50 
 * @Version: V1.0
 **/
@Controller
@RequestMapping("user")
public class UserLoginController {
    @Autowired
    private UserService userService;
    /**
     * 用来处理身份认证
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password, String code, HttpSession session, Model model) {
        try {
                System.out.println("进入login的登录方法====");
                //获取主体对象
                Subject subject = SecurityUtils.getSubject();
                //当执行这个login的方法，就会触发在自定义的CustomerRealm的AuthenticationInfo的方法
                subject.login(new UsernamePasswordToken(username, password));//!!!!
                return "redirect:/index.jsp";
            } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
            System.out.println("密码错误!");
            model.addAttribute("error","密码错误");
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("登录页面");
        return "redirect:/login.jsp";
    }
    /**
     * 退出登录
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();//退出用户
        return "redirect:/login.jsp";
    }
    /**
     * 用户注册
     */
    @RequestMapping("register")
    public String register(User user) {
        try {
            System.out.println("进入userController 层了：！！！！！");
            if(user!=null&&user.getPassword()!=null){
                System.out.println("user的信息不为null:");
                userService.register(user);
                return "redirect:/login.jsp";
            }
            return "redirect:/register.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }
}
