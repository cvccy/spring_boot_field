package com.cvccy.jul;

import com.cvccy.log4j.Log4jTest;

import java.util.logging.Logger;

/**
 * jul可已通过适配器，统一日志架构为log4j
 *
 */
public class JulTest {
    public static void main(String[] args) {

        Logger logger = Logger.getLogger(JulTest.class.getName());
        //logger.trace("trace");
        logger.finest("finest");
        logger.info("info");

       //jul中log4j的接口不完全能用

        /*ogger.trace("trace");
        logger.error("fist error");
        logger.info("info");*/

    }
}
