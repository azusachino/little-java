package cn.az.java.cloud.command;

import cn.hutool.core.thread.ThreadUtil;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author az
 * @since 2020-04-14
 */
public class FutureCircuitBreakerDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 初始化线程池
        ExecutorService executorService = ThreadUtil.newSingleExecutor();

        RandomCommand command = new RandomCommand();

        Future<String> future = executorService.submit(command::run);

        String result;
        // 100 毫秒超时时间
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            // fallback 方法调用
            result = command.fallback();
        }

        System.out.println(result);

        executorService.shutdown();

    }

    /**
     * 随机对象
     */
    private static final Random RANDOM = new Random();

    /**
     * 随机事件执行命令
     */
    static class RandomCommand implements Command<String> {


        @Override
        public String run() throws InterruptedException {

            long executeTime = RANDOM.nextInt(200);

            // 通过休眠来模拟执行时间
            System.out.println("Execute Time : " + executeTime + " ms");
            Thread.sleep(executeTime);

            return "Hello,World";
        }

        @Override
        public String fallback() {
            return "Fallback";
        }
    }

    public interface Command<T> {

        /**
         * 正常执行，并且返回结果
         *
         * @return t
         */
        T run() throws Exception;

        /**
         * 错误时，返回容错结果
         *
         * @return t
         */
        T fallback();

    }
}
