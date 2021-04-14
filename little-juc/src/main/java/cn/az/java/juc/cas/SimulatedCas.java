package cn.az.java.juc.cas;

/**
 * 描述：     模拟CAS操作，等价代码
 *
 * @author az
 */
public class SimulatedCas {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
