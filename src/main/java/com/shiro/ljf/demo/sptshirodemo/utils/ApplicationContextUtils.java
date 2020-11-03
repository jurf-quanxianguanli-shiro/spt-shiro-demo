package com.shiro.ljf.demo.sptshirodemo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ApplicationContextUtils
 * @Description: TODO
 * @Author: liujianfu
 * @Date: 2020/11/03 21:14:43 
 * @Version: V1.0
 **/
@Component   //此注解记住一定要加，在CustomerRealm中的ApplicationContextUtils获取时，才会不报空指针
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
    //根据bean名字获取工厂中指定bean 对象
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
