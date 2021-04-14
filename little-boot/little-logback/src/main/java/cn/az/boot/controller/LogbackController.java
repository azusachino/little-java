package cn.az.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author az
 */
@RestController
public class LogbackController {


    private final Logger logger = LoggerFactory.getLogger(LogbackController.class.getName());

    @RequestMapping("/logback")
    public String log(@RequestParam String message) {

        String log = "Logback : " + message;

        logger.info(log);

        return log;
    }
}
