package cn.az.java.juc.cache;

import cn.az.java.juc.cache.computable.ExpensiveFunction;
import cn.hutool.core.thread.ThreadUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 描述：
 *
 * @author az
 */
public class ImoocCache12 {

    /**
     * The Expensive computer.
     */
    static ImoocCache10<String, Integer> expensiveComputer = new ImoocCache10<>(
        new ExpensiveFunction());
    /**
     * The constant countDownLatch.
     */
    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = ThreadUtil.newExecutor(100);
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                Integer result = null;
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待");
                    countDownLatch.await();
                    SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatter.get();
                    String time = dateFormat.format(new Date());
                    System.out.println(Thread.currentThread().getName() + "   " + time + "被放行");
                    result = expensiveComputer.compute("666");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }

        Thread.sleep(5000);
        countDownLatch.countDown();
        service.shutdown();
    }

    /**
     * The type Thread safe formatter.
     */
    static class ThreadSafeFormatter {

        /**
         * The constant dateFormatter.
         */
        public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<>() {

            //每个线程会调用本方法一次，用于初始化
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("mm:ss");
            }

            //首次调用本方法时，会调用initialValue()；后面的调用会返回第一次创建的值
            @Override
            public SimpleDateFormat get() {
                return super.get();
            }
        };
    }

}
