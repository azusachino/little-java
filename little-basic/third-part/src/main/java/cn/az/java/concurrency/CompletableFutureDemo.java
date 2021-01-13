package cn.az.java.concurrency;

import java.util.concurrent.CompletableFuture;

/**
 * @author az
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        //  CompletableFuture

        CompletableFuture.supplyAsync(Object::new).thenApply(String::valueOf)
            // 异常的方式结束
            .completeExceptionally(new RuntimeException())
        ;
    }
}
