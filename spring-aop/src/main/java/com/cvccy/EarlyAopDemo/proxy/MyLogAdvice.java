package com.cvccy.EarlyAopDemo.proxy;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author xhf
 * @version V1.0
 * @date 2020/9/26 8:50 上午
 * @description
 */
public class MyLogAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String name = method.getName();
        System.out.println("执行目标方法【"+name+"】的<前置通知>,入参"+ Arrays.asList(objects));
    }
}
