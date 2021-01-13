package cn.az.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author azusachino
 * @version 12/11/2019
 */
@Slf4j
@Service
public class AsyncService {

    @Async("async-method")
    public Future<String> asyncMethod() {
        sleep();
        log.info("asyncMethod-innerThreadName：{}", Thread.currentThread().getName());
        return new AsyncResult<>("Hello Async");
    }

    @Async
    public CompletableFuture<?> asyncAction() {
        return CompletableFuture.supplyAsync(() -> "Hello World");
    }

    public void syncMethod() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
