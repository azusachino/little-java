package cn.az.java.juc.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author az
 */
@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        // simple AQS only two threads
        new Thread(() -> {
            reentrantLock.lock();
            try {
                log.info("wait signal"); // 1
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("get signal"); // 4
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                log.info("get lock"); // 2
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                condition.signalAll();
                log.info("send signal ~ "); // 3
                reentrantLock.unlock();
            }
        }).start();
    }
}
