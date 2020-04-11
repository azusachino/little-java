package cn.az.java.juc.future;

import java.util.concurrent.*;

/**
 * 描述：     演示get方法过程中抛出异常，for循环为了演示抛出Exception的时机：并不是说一产生异常就抛出，直到我们get执行时，才会抛出。
 * @author az
 */
public class GetException {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        Future<Integer> future = service.submit(new CallableTask());


        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            System.out.println(future.isDone());
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println(e.getClass().getName() + "异常");
        }
    }


    static class CallableTask implements Callable<Integer> {

        @Override
        public Integer call() {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }
}
