package com.shiro.ljf.demo.sptshirodemo.shiro;

import com.shiro.ljf.demo.sptshirodemo.entity.User;
import com.shiro.ljf.demo.sptshirodemo.service.UserService;
import com.shiro.ljf.demo.sptshirodemo.utils.ApplicationContextUtils;
import com.shiro.ljf.demo.sptshirodemo.utils.MyByteSource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName: CustomerRealm
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/28 15:17:48 
 * @Version: V1.0
 **/
public class CustomerRealm extends AuthorizingRealm {
    /**
    * @author liujianfu
    * @description
    * @date 2020/10/28 16:54
    * @return org.apache.shiro.authz.AuthorizationInfo
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权部分======:");
        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证: "+primaryPrincipal);
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
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),
                    new MyByteSource(user.getSalt()),
                    this.getName());
        }
        return null;
    }

}
