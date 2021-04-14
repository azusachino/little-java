package cn.az.boot.reactive.loader;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author az
 */
public class ParallelDataLoader extends DataLoader {

    public static void main(String[] args) {
        ParallelDataLoader pdl = new ParallelDataLoader();
        pdl.doLoad();
    }

    @Override
    protected void doLoad() {
        ExecutorService executorService = new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().build());
        CompletionService<?> completionService = new ExecutorCompletionService<>(executorService);
        completionService.submit(super::loadConfigurations, null);
        completionService.submit(super::loadUsers, null);
        completionService.submit(super::loadOrders, null);
        int count = 0, max = 3;
        while (count < max) {
            // 等待三个任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
    }
}
