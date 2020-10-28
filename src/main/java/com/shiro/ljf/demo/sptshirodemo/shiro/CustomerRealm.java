package com.shiro.ljf.demo.sptshirodemo.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
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
        System.out.println("认证");
        return null;
    }
}
