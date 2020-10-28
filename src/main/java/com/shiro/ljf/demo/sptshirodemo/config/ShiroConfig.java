package com.shiro.ljf.demo.sptshirodemo.config;

import com.shiro.ljf.demo.sptshirodemo.shiro.CustomerRealm;

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
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/10/28 15:11:16 
 * @Version: V1.0
 **/
@Configuration
public class ShiroConfig {
    //1.创建shiroFilter ：负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){

        //创建shiro的filter，配置shiroFilterFactoryBean
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注入安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //设置过滤资源
        Map<String,String> map =  new LinkedHashMap<>();
        map.put("/**","authc");
        /** 代表拦截项目中一切资源  authc 代表shiro中的一个filter的别名,详细内容看文档的shirofilter列表**/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }
    //2.创建安全管理器
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);

        return defaultWebSecurityManager;
    }
    //3.创建自定义realm
    @Bean(name="userRealm")
    public Realm getRealm(){
        return new CustomerRealm();
    }
}
