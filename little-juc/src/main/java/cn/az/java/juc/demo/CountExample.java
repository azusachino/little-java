package cn.az.java.juc.demo;

import cn.hutool.core.thread.ThreadUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author az
 * @date 4/4/2020
 */
public class CountExample {

    private static Log log = LogFactory.getLog(CountExample.class);

    private static int total = 5000;

    private static int threadTotal = 200;

    // private static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ExecutorService service = ThreadUtil.newExecutor();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch cdl = new CountDownLatch(total);

        Stream.iterate(1, i -> i + 1).limit(total)
                .forEach(i -> {
                    service.submit(() -> {
                        try {
                            semaphore.acquire();
                            add();
                            semaphore.release();
                        } catch (Exception e) {
                            log.error(e);
                        }
                        cdl.countDown();
                    });
                });
        cdl.await();
        log.info("count: " + count.get());

        service.shutdown();
    }

    private static void add() {
        // count++;
        count.getAndIncrement();
    }
}
