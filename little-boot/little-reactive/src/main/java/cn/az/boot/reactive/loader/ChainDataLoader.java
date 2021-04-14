package cn.az.boot.reactive.loader;

import java.util.concurrent.CompletableFuture;

/**
 * @author az
 */
public class ChainDataLoader extends DataLoader {

    public static void main(String[] args) {
        ChainDataLoader cdl = new ChainDataLoader();
        cdl.doLoad();
    }

    @Override
    protected void doLoad() {
        CompletableFuture.runAsync(super::loadConfigurations)
            .thenRun(super::loadUsers)
            .thenRun(super::loadOrders)
            .whenComplete((r, t) -> {
                System.out.println(Thread.currentThread().getName() + " is completing all tasks");
            })
            .join();
    }
}
