package cn.az.boot.controller;

import cn.az.boot.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author azusachino
 * @version 12/11/2019
 */
@Slf4j
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("async")
    public String testAsync() throws Exception{
        long start = System.currentTimeMillis();
        log.info("async start");
        Future<String> stringFuture = asyncService.asyncMethod();
        String result = stringFuture.get(60, TimeUnit.SECONDS);
        asyncService.asyncAction().join();
        log.info("async result: {}", result);
        log.info("async end");

        long end = System.currentTimeMillis();
        log.info("duration: {}", end - start);
        return stringFuture.get();
    }

    @GetMapping("sync")
    public void testSync() {
        long start = System.currentTimeMillis();
        log.info("同步方法开始");

        asyncService.syncMethod();

        log.info("同步方法结束");
        long end = System.currentTimeMillis();
        log.info("总耗时：{} ms", end - start);
    }
}
