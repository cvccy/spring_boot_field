package com.cvccy.EarlyAopDemo.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author xhf
 * @version V1.0
 * @date 2020/9/26 8:52 上午
 * @description
 */
public class MyLogInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(getClass()+"：调用方前");
        Object ret = methodInvocation.proceed();
        System.out.println(getClass()+":调用方法后");
        return ret;
    }
}
