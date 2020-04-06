package cn.az.java.juc.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author az
 * @date 4/4/2020
 */
public class AtomicReferenceDemo {

    private static Log log = LogFactory.getLog(AtomicReferenceDemo.class);

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    private static final AtomicIntegerFieldUpdater<AtomicReferenceDemo> UPDATER = AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceDemo.class, "a");

    private volatile int a = 100;

    public static void main(String[] args) {
        count.compareAndExchange(0, 1);
        count.compareAndExchange(1, 2);
        count.compareAndExchange(0, 1);
        count.compareAndExchange(3, 1);
        count.compareAndExchange(2, 99);

        log.info(count.get());

        AtomicReferenceDemo atomicReferenceDemo = new AtomicReferenceDemo();

        UPDATER.compareAndSet(atomicReferenceDemo, 100, 102);

        log.info(UPDATER.get(atomicReferenceDemo));
    }
}
