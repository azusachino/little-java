package cn.az.boot.log;

import java.util.logging.Logger;

/**
 * @author az
 */
public class LogTest {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(LogTest.class.getName());
        logger.info("Hello, World");
    }
}
