package cn.az.java.juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * 描述: 1000个打印日期的任务，用线程池来执行
 *
 * @author az
 */
public class ThreadLocalNormalUsage02 {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(10, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(30), Executors.privilegedThreadFactory());

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                String date = new ThreadLocalNormalUsage02().date(finalI);
                System.out.println(date);
            });
        }
        threadPool.shutdown();
    }

    public String date(int seconds) {
        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
