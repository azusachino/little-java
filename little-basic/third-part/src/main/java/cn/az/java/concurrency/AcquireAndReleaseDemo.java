package cn.az.java.concurrency;

/**
 * @author az
 */
public class AcquireAndReleaseDemo {

    public static void main(String[] args) {
        //　Lock　机制
        // 获得 Acquire
            // Thread 进入 synchronized -> 获得锁 | Lock.lock()
        // 释放 Release
            // 1. 当 Thread#holdsLock(obj)，调用 Object#wait() 时候
            // 2. Thread park -> LockSupport.park(Object)
            // 3. Condition#await()
            // 4. 运行期异常，Thread 消亡
            // 5. Java 9 自旋 Thread.onSpinWait();
            // 6. Thread.yield();

        // 所谓的公平（Fair）和非公平（NonFair、unfair）
        // 公平（Fair）线程 FIFO
        // 非公平（NonFair）线程随线程调度
        // 最佳实践：在创建线程时，除非必要，不要调整线程优先级（Priority）

        // Fair
        // t1 -> t2 -> t3
        // t1.unlock() if t3 acquire `lock`, park(t3), t2.lock() -> t2.unlock -> un park(t3) -> t3.lock()
    }
}
