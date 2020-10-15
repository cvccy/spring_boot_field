package com.cvccy.slf4japi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jApi {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Slf4jApi.class);
        logger.debug("debug");

    }
}
