package cn.az.java.concurrency;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author az
 */
public class DeadLockDemo {

    public static void main(String[] args) {

        final Object m1 = new Object();
        final Object m2 = new Object();

        ExecutorService service = ThreadUtil.newExecutor(2);

        service.submit(() -> {
            synchronized (m1) {
                System.out.printf("Thread[ ID : %d] holds m1\n", Thread.currentThread().getId());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (m2) {
                    System.out.printf("Thread[ ID : %d] holds m2\n", Thread.currentThread().getId());
                }
            }
        });

        service.submit(() -> {
            synchronized (m2) {
                System.out.printf("Thread[ ID : %d] holds m2\n", Thread.currentThread().getId());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (m1) {
                    System.out.printf("Thread[ ID : %d] holds m1\n", Thread.currentThread().getId());
                }
            }
        });

        service.shutdown();
    }
}
