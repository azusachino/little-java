package cn.az.java.concurrency;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author az
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 4 -> 3 -> 2 -> 1
        // 4 个计数
        CountDownLatch latch = new CountDownLatch(4);
        // 线程数 3
        ExecutorService executorService = ThreadUtil.newExecutor(3);

        // 启动3个线程
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    echoThread(latch.getCount());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // -1
                    latch.countDown();
                }
            });
        }

        Thread.sleep(5 * 100L);

        // 当 count 数量等于 0，才会释放
        // 4-3 == 1 !=0
        System.out.println("CountDownLatch 剩余数量：" + latch.getCount());
        latch.await(); // await 类似 wait() -> join

        // 关闭线程池
        executorService.shutdown();

    }

    private static void echoThread(long count) {
        System.out.printf("当前线程[%s]正在执行, count is [%d]\n", Thread.currentThread().getName(), count);
    }
}
