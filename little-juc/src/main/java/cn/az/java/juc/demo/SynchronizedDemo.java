package cn.az.java.juc.demo;

import cn.hutool.core.thread.ThreadUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ExecutorService;

/**
 * @author az
 */
public class SynchronizedDemo {

    private static Log log = LogFactory.getLog(SynchronizedDemo.class);

    public static void main(String[] args) {
        ExecutorService service = ThreadUtil.newExecutor();
        SynchronizedDemo s1 = new SynchronizedDemo();
        SynchronizedDemo s2 = new SynchronizedDemo();

        service.submit(() -> s1.t1(1));
        service.submit(() -> s1.t2(2));

        service.shutdown();

    }

    private void t1(int j) {
        // 修饰当前对象 (不同对象之间互相不影响)
        synchronized (this) {
            // synchronized(SynchronizedDemo.class) 修饰整个类
            for (int i = 0; i < 10; i++) {
                log.info("t1 - " + j + " - " + i);
            }
        }
    }

    private synchronized void t2(int j) {
        // 修饰单一方法
        for (int i = 0; i < 10; i++) {
            log.info("t2 - " + j + " - " + i);
        }

    }
}
