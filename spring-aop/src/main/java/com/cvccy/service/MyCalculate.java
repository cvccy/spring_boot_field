package com.cvccy.service;

import org.springframework.stereotype.Service;

/**
 * @author xhf
 * @version V1.0
 * @date 2020/9/26 8:46 上午
 * @description
 */
@Service
public class MyCalculate implements Calculate{
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }

    @Override
    public int multiplay(int a, int b) {
        return a*b;
    }

    @Override
    public int sum(int a, int b) {
        return 0;
    }

    @Override
    public int div(int a, int b) {
        return a/b;
    }

    @Override
    public int mod(int a, int b) {
        return a%b;
    }
}
