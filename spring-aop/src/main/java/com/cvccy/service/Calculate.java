package com.cvccy.service;

/**
 * @author xhf
 * @version V1.0
 * @date 2020/9/26 8:28 上午
 * @description 计算机接口类
 */
public interface Calculate {

    /**
     * @description 加法
     * @param a
     * @param b
     * @return add result
     */
    int add(int a, int b);

    /**
     * 减法
     * @param a
     * @param b
     * @return
     */
    int sub(int a, int b);

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    int multiplay(int a, int b);

    /**
     * 求和
     * @param a
     * @param b
     * @return
     */
    int sum(int a, int b);

    /**
     * 除
     * @param a
     * @param b
     * @return
     */
    int div(int a, int b);

    int mod(int a, int b);

}

