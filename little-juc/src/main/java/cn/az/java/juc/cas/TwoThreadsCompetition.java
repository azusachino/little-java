package cn.az.java.juc.cas;

/**
 * 描述： 模拟CAS操作，等价代码
 *
 * @author az
 */
public class TwoThreadsCompetition implements Runnable {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;
        Thread t1 = new Thread(r, "Thread 1");
        Thread t2 = new Thread(r, "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + compareAndSwap(0, 1));
    }
}
