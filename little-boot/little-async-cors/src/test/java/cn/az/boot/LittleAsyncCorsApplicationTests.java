package cn.az.boot;

import cn.az.boot.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
class LittleAsyncCorsApplicationTests {

    @Autowired
    private AsyncService asyncService;

    @Test
    void contextLoads() {
    }

    @Test
    public String testAsync() throws Exception {
        long start = System.currentTimeMillis();
        log.info("async start");
        Future<String> stringFuture = asyncService.asyncMethod();
        String result = stringFuture.get(60, TimeUnit.SECONDS);
        log.info("async result: {}", result);
        log.info("async end");

        long end = System.currentTimeMillis();
        log.info("duration: {}", end - start);
        return stringFuture.get();
    }

}
