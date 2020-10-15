package com.cvccy.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class JclTest {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(JclTest.class);
        log.trace("trace");
        log.error("fist error");
        //System.out.println(log.toString());
    }
}
