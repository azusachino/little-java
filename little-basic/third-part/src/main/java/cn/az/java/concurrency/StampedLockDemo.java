package cn.az.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author az
 */
public class StampedLockDemo {

    public static void main(String[] args) {
        // Java 1.8 之后提供
        StampedLock lock = new StampedLock();
        long stamp = lock.tryOptimisticRead();
        Lock readLock = lock.asReadLock();
        readLock.lock();
        try {
            lock.validate(stamp);
        } finally {
            readLock.unlock();
        }

        Point point = new Point();

        point.moveIfAtOrigin(40, 12);
        point.move(1, 2);
        point.moveIfAtOrigin(40, 12);
    }
}
