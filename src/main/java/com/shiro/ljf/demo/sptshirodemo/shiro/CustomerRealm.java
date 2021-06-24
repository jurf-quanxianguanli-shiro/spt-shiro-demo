package com.shiro.ljf.demo.sptshirodemo.shiro;

import com.shiro.ljf.demo.sptshirodemo.entity.MenuPerms;
import com.shiro.ljf.demo.sptshirodemo.entity.User;
import com.shiro.ljf.demo.sptshirodemo.service.UserService;
import com.shiro.ljf.demo.sptshirodemo.utils.ApplicationContextUtils;
import com.shiro.ljf.demo.sptshirodemo.utils.MyByteSource;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import java.util.List;

/**
 * @ClassName: CustomerRealm
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/28 15:17:48 
 * @Version: V1.0
 **/
public class CustomerRealm extends AuthorizingRealm {
    private static final Logger log= LoggerFactory.getLogger(CustomerRealm.class);
    /**
     * @author liujianfu
     * @description    授权方法
     * @date 2020/10/28 16:54
     * @return org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权部分======:");
        //获取身份信息
       String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证: "+primaryPrincipal);
       // User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        //System.out.println("调用授权验证: "+primaryPrincipal.getUsername());
        /**
         if("ljf".equals(primaryPrincipal)){//登录的用户ljf，具有以下角色
         //角色的授权
         SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
         simpleAuthorizationInfo.addRole("admin");
         simpleAuthorizationInfo.addRole("guest");
         //权限字符串的授权
         simpleAuthorizationInfo.addStringPermission("haha:add:01");
         simpleAuthorizationInfo.addStringPermission("haha:update:02");
         return simpleAuthorizationInfo;
         }
         **/
        //根据主身份信息获取角色 和 权限信息
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findRolesByUserName(primaryPrincipal);
        //User user = userService.findRolesByUserName(primaryPrincipal.getUsername());//不使用密码注册器，这样写
        //授权角色信息
        if(!CollectionUtils.isEmpty(user.getRoles())){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role->{
                simpleAuthorizationInfo.addRole(role.getName());
                //权限信息
                List<MenuPerms> perms = userService.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm->{
                        simpleAuthorizationInfo.addStringPermission(perm.getName());
                    });
                }
            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
   * @author liujianfu
   * @description
   * @date 2020/10/28 16:54
   * @return org.apache.shiro.authc.AuthenticationInfo
   */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("============================================进入认证方法==================");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        final String userName=token.getUsername();
        final String password=String.valueOf(token.getPassword());

        log.info("用户名: {} 密码：{}",userName,password);
        /**      写死的部分
        //获取身份信息
        String primaryPrincipal = (String) authenticationToken.getPrincipal();
        System.out.println("调用验证: "+primaryPrincipal);
        //根据主身份信息获取角色 和 权限信息
        if("admin".equals(primaryPrincipal)) {
            System.out.println("this name:"+this.getName());
            return new SimpleAuthenticationInfo(primaryPrincipal,"123",this.getName());
        }

        return null;
         **/

        //根据身份信息   ,动态从数据库中加载数据
        String principal = (String) authenticationToken.getPrincipal();
        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findByUserName(principal);
        if(!ObjectUtils.isEmpty(user)){

            //设置用户名，密码，随机盐
             /**  下面语句包含此逻辑的判断,使用此段代码，需要将shiroconfig中的密码注册器注释掉
             Md5Hash md5Hash2 = new Md5Hash(password, user.getSalt(), 1024);
             String realPassword=md5Hash2.toHex();
             System.out.println("加密后:"+realPassword);
             if (StringUtils.isBlank(realPassword) || !realPassword.equals(user.getPassword())){
             throw new IncorrectCredentialsException("账户密码不匹配!");
             }
             //这里password传入明文
            return new SimpleAuthenticationInfo(user,password ,this.getName());
              **/

            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),
                    new MyByteSource(user.getSalt()),
                    this.getName());

        }
        return null;
    }

}
