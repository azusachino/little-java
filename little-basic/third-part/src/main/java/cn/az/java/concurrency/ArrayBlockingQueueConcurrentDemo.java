package cn.az.java.concurrency;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author az
 */
public class ArrayBlockingQueueConcurrentDemo {

    public static void main(String[] args) throws InterruptedException {

        // 最大允许添加 2 个元素
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2, true);
        // 申请 2 个大小的线程池
        ExecutorService executorService = ThreadUtil.newExecutor(2);

        for (AtomicInteger i = new AtomicInteger(1); i.get() < 100; i.incrementAndGet()) {
            // 写线程（1）
            executorService.submit(() -> {
                try {
                    queue.put(i.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // 读线程（1）
            executorService.submit(() -> {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.awaitTermination(10, TimeUnit.SECONDS);
        // 关闭线程池
        executorService.shutdown();

    }
}
