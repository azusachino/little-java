package cn.az.java.juc.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author az
 */
public class PessimismOptimismLock {

    private static int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        Stream.iterate(1, i -> i + 1).limit(20).forEach(i -> {
            atomicInteger.incrementAndGet();
            testMethod();
        });
        System.out.println("atomic :" + atomicInteger.get());
        System.out.println("a: " + PessimismOptimismLock.a);
    }

    public static void testMethod() {
        a++;
    }


}
