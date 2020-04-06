package cn.az.java.juc.atomic;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * 描述: 演示高并发场景下，LongAdder比AtomicLong性能好
 *
 * @author az
 */
public class LongAdderDemo {

    private static int MAX_COUNT = 10000;

    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
        ExecutorService service = ThreadUtil.newExecutor(20);
        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i++) {
            service.submit(new Task(counter));
        }
        service.shutdown();
        while (!service.isTerminated()) {
            // spinlock
        }
        long end = System.currentTimeMillis();
        System.out.println(counter.sum());
        System.out.println("LongAdder耗时：" + (end - start));
    }

    private static class Task implements Runnable {

        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_COUNT; i++) {
                counter.increment();
            }
        }
    }
}
