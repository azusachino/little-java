package cn.az.boot.reactive.loader;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author az
 */
public class FutureBlockingDataLoader extends DataLoader {

    public static void main(String[] args) {
        FutureBlockingDataLoader fdl = new FutureBlockingDataLoader();
        fdl.doLoad();
    }

    @Override
    protected void doLoad() {
        //Future#get() 方法不得不等待任务执行完成，换言之，如果多个任务提交后，返回的多个 Future 逐一调用 get() 方法时，将会依次 blocking，任务的执行从并行变为串行。
        ExecutorService executorService = new ThreadPoolExecutor(3, 4, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().build());
        runCompletely(executorService.submit(super::loadConfigurations));
        runCompletely(executorService.submit(super::loadUsers));
        runCompletely(executorService.submit(super::loadOrders));

        executorService.shutdown();
    }

    private void runCompletely(Future<?> future) {
        try {
            future.get();
        } catch (Exception ignored) {
        }
    }
}
