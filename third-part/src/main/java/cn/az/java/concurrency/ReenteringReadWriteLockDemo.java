package cn.az.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author az
 */
public class ReenteringReadWriteLockDemo {

    public static void main(String[] args) {

        ReadWriteLock lock = new ReentrantReadWriteLock();

        Lock readLock = lock.readLock();

        Lock writeLock = lock.writeLock();

    }
}
