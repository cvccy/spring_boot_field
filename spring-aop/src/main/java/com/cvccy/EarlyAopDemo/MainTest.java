package com.cvccy.EarlyAopDemo;

import com.cvccy.EarlyAopDemo.config.MyConfig;
import com.cvccy.service.Calculate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xhf
 * @version V1.0
 * @date 2020/9/26 9:02 上午
 * @description
 */
public class MainTest {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        //通过ProxyFactoruBean创建的代理类
        Calculate calculateProxy = context.getBean("calculateProxy",Calculate.class);
        System.out.println(calculateProxy.add(1,1));
        System.out.println("-----------");
        System.out.println(calculateProxy.div(4, 2));

        //通过NameMatchMethodPointcutAdvitor创建的代理
        /*Calculate calculateProxy = context.getBean("calculateProxy", Calculate.class);
        calculateProxy.add(1,1);*/

        /*Calculate calculateProxy = context.getBean("myCalculate", Calculate.class);
        calculateProxy.add(1,1);*/
    }

}
