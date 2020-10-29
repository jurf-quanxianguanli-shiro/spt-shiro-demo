package com.shiro.ljf.demo.sptshirodemo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

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
        System.out.println("授权");
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
        //获取身份信息
        String primaryPrincipal = (String) authenticationToken.getPrincipal();
        System.out.println("调用授权验证: "+primaryPrincipal);
        //根据主身份信息获取角色 和 权限信息
        if("admin".equals(primaryPrincipal)) {
            System.out.println("this name:"+this.getName());
            return new SimpleAuthenticationInfo(primaryPrincipal,"123",this.getName());
        }

        return null;

    }
}
