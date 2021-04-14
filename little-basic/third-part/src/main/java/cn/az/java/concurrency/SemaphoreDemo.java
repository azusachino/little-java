package cn.az.java.concurrency;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * @author az
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {

        Semaphore cb = new Semaphore(4);

        ExecutorService executorService = ThreadUtil.newExecutor(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                try {
                    echoThread();
                    cb.acquire();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(5 * 100L);

        // 关闭线程池
        executorService.shutdown();

    }


    private static void echoThread() {
        System.out.printf("当前线程[%s]正在执行...\n", Thread.currentThread().getName());
    }

}
