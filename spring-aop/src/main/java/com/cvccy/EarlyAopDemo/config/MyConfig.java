package com.cvccy.EarlyAopDemo.config;

import com.cvccy.EarlyAopDemo.proxy.MyLogAdvice;
import com.cvccy.EarlyAopDemo.proxy.MyLogInterceptor;
import com.cvccy.service.Calculate;
import com.cvccy.service.MyCalculate;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * TODO
 *
 * @author xhf
 * @version V1.0
 * @date 2020/9/26 8:55 上午
 * @description
 */
@Configuration
@ComponentScan("com.cvccy")
@EnableAspectJAutoProxy //开启AspectJ
public class MyConfig {

    /**
     * 被代理对象
     * @return
     */
    @Bean
    public Calculate myCalculate(){
        return new MyCalculate();
    }

    /**
     * Advice方式代理
     * @return
     */
    @Bean
    public MyLogAdvice myLogAdvice(){
        return new MyLogAdvice();
    }

    /**
     * Interceptor方式，类似环绕通知
     * @return
     */
    @Bean
    public MyLogInterceptor myLogInterceptor(){
        return new MyLogInterceptor();
    }

    /**
     * 使用责任链方式对advice和Interceptor进行调用
     * 通过FactoryBean的getObject方法创建一个代理实现类
     * 代理模式
     * 存在问题：
     * * * 只能指定单一Bean的AOP，若存在多个Bean需要创建多个ProxyFactoryBean。
     * * * 拦截粒度只控制在类级别，类中所有的方法都被拦截
     *
     * @return
     */
    /*@Bean
    public ProxyFactoryBean calculateProxy(){
        ProxyFactoryBean myService = new ProxyFactoryBean();
        //myService.setInterceptorNames("myLogAdvice","myLogInterceptor"); //根据顺序执行
        myService.setInterceptorNames("myLogAspect");
        myService.setTarget(myCalculate());
        return myService;
    }*/

    /**
     * 粒度强化，精确到方法
     * @return
     */
   /* @Bean
    public NameMatchMethodPointcutAdvisor myLogAspect(){
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setAdvice(myLogAdvice());
        advisor.setMappedName("add");
        return advisor;
    }*/

    //BeanNameAutoProxyCreate

    /**
     * autoProxy:BeanPostProcessor 手动执行Advice方式，BeanNameAutoProxyCreater
     * @return
     */
    /*@Bean
    public BeanNameAutoProxyCreator calculateAutoProxy(){
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setBeanNames("my*");            //设置要创建道理的哪些Neam的名字
        creator.setInterceptorNames("myLogInterceptor"); //设置连接拦截凉名字，有顺序
        return creator;
    }*/
    //DefaultAdvisorAutoProxyCretor








}
