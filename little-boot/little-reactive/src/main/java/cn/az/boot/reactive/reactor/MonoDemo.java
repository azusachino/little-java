package cn.az.boot.reactive.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author az
 * @date 2020/4/1
 */
public class MonoDemo {

    public static void main(String[] args) {
        Mono.create(MonoSink::success)
                .block();
    }

    public void createMono() {
        // Creating a Mono containing "Hello World !".
        Mono<String> helloWorld = Mono.just("Hello World !");

        // Creating an empty Mono
        Mono<?> empty = Mono.empty();
        // Creating a mono from a Callable
        Mono<String> helloWorldCallable = Mono.fromCallable(() -> "Hello World !");
        // Same with Java 8 method reference
        Mono<?> user = Mono.fromCallable(MonoDemo::createString);

        CompletableFuture<String> helloWorldFuture = CompletableFuture.supplyAsync(() -> "");
        // Creating a mono from a Future
        Mono<String> monoFromFuture = Mono.fromFuture(helloWorldFuture);

        // Creating a mono from a supplier
        Random rnd = new Random();
        Mono<Double> monoFromSupplier = Mono.fromSupplier(rnd::nextDouble);

        // You can also create a mono from another one.
        Mono<Double> monoCopy = Mono.from(monoFromSupplier);

        // Or from a Flux.
        Mono<Integer> monoFromFlux = Mono.from(Flux.range(1, 10));

        // The above Mono contains the first value of the Flux.
    }

    public static String createString() {
        return "";
    }

    // Both example give the same result
    Integer getAnyInteger() throws Exception {
        throw new RuntimeException("An error as occured for no reason.");
    }

    // Now, comparison between the two methods
    void compareMonoCreationMethods() {
        Mono<Integer> fromCallable = Mono.fromCallable(this::getAnyInteger);
        // result -> Mono.error(RuntimeException("An error as occured for no reason."))

        Mono<Integer> defer = Mono.defer(() -> {
            try {
                Integer res = this.getAnyInteger();
                return Mono.just(res);
            } catch (Exception e) {
                return Mono.error(e);
            }
        });
        // result -> Mono.error(RuntimeException("An error as occured for no reason."))
    }

    void monoCreateExample() {
        Mono<Integer> m = Mono.create(callback -> {
            try {
                callback.success(this.getAnyInteger());
            } catch (Exception e) {
                callback.error(e);
            }
        });
    }
}
