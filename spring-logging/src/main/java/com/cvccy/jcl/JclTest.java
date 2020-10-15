package com.cvccy.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * common-logging.jar包中的logger简称jcl是日志统一的接口门面，同样功能的好友slf4j
 * jcl根据classpath中的jar包选择日志架构
 * log4j优先级最高依次是jul、Simplelog
 *
 * 效率低、使用ClassLoad是会出现OOM
 * 优先使用slf4j
 */
public class JclTest {
    public static void main(String[] args) {

        Log log = LogFactory.getLog(JclTest.class);
        log.trace("trace");
        log.error("fist error");
        //System.out.println(log.toString());
    }
}
