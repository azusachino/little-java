package cn.az.boot.cache.hash;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author az
 */
public class HashTest {

    private volatile int data;

    private synchronized void doSome() {

        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println("wa");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Integer var1 = 1, var2 = 2;
        System.out.println(var1.hashCode());
        System.out.println(var2.hashCode());

        System.out.println(System.identityHashCode(var1));
        System.out.println(System.identityHashCode(var2));
    }
}
