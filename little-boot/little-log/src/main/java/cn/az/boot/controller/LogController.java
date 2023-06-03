package cn.az.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.az.boot.service.LogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author azusachino
 * @version 12/16/2019
 */
@Slf4j
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/a")
    public String hello() {
        log.info("a start");
        String aaa = logService.getA();
        log.info(aaa);
        return aaa;
    }
}
