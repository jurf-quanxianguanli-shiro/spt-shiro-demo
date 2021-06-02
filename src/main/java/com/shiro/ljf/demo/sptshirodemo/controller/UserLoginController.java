package com.shiro.ljf.demo.sptshirodemo.controller;

import com.shiro.ljf.demo.sptshirodemo.entity.User;
import com.shiro.ljf.demo.sptshirodemo.service.UserService;
import com.shiro.ljf.demo.sptshirodemo.utils.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

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
    public String login(String username, String password, String myVerifyCode, HttpSession session, Model model) {
        //比较验证码
        String codes = (String) session.getAttribute("code");
        try {
            System.out.println("进入login的登录方法====");
            if (codes!=null&&codes.equalsIgnoreCase(myVerifyCode)){
                //获取主体对象
                Subject subject = SecurityUtils.getSubject();
                Serializable sessionId = subject.getSession().getId();
                System.out.println("sessionid:"+sessionId);
                //当执行这个login的方法，就会触发在自定义的CustomerRealm的AuthenticationInfo的方法
                subject.login(new UsernamePasswordToken(username, password));//!!!!
                return "redirect:/index.jsp";

            }
            else{
                model.addAttribute("error","验证码错误!");
                throw new RuntimeException("验证码错误!");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
            model.addAttribute("error","密码错误");
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("登录页面");
     //   return "redirect:/login.jsp";   //重定向，携带参数，页面无法接收
        return "login";  //跳转到login.jsp页面   改成服务端跳转
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
    /**
     * work
     */
    @RequestMapping("work")
    public String work() {
        try {
            System.out.println("进入userController 层了work：！！！！！");

                return "redirect:/order.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }
    /**
     * 验证码方法
     */
    @RequestMapping("getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("code",code);
        //验证码存入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220,60,os,code);
    }
}
