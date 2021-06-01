package com.shiro.ljf.demo.sptshirodemo.config;

import com.shiro.ljf.demo.sptshirodemo.shiro.CustomerRealm;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description: TODO   整合springboot的shiro配置类
 * @Author: liujianfu
 * @Date: 2020/10/28 15:11:16 
 * @Version: V1.0
 **/
@Configuration
public class ShiroConfig {
    //1.创建shiroFilter ：负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        System.out.println("step3：>>进入过滤器");
        //创建shiro的filter，配置shiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置过滤资源
        Map<String,String> map =  new LinkedHashMap<>();
        //map.put("/**","authc");
        /** 代表拦截项目中一切资源  authc 代表shiro中的一个filter的别名,详细内容看文档的shirofilter列表**/
        //map.put("/index.jsp","authc");//验证资源

      //  map.put("/index*","anon");//验证资源 anon  匿名访问，可以跳转到index要访问的controller
        //设置公共资源 ,anno过滤器，设置访问公共资源，放在前面
        map.put("/user/login","anon");
        map.put("/user/register","anon");//anon 设置为公共资源  放行资源放在前面
        map.put("/register.jsp","anon");//anon 设置为公共资源  放行资源放在前面
        //设置受限资源

        map.put("/index*","authc");//验证资源，受限资源，跳转登录login.jsp页面
        //默认认证跳转路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //2.创建安全管理器
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") Realm realm){
        System.out.println("step2：>> 进入securityManager方法");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }
    //3.创建自定义realm
    @Bean(name="userRealm")
    public Realm getRealm(){
        System.out.println("step1：>>进入realm方法");
        //return new CustomerRealm();
        CustomerRealm customerRealm = new CustomerRealm();

        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
}
