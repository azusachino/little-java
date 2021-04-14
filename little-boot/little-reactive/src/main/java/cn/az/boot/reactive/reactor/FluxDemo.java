package cn.az.boot.reactive.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Random;

/**
 * @author az
 */
public class FluxDemo {

    public static void main(String[] args) {
        Flux.just("a", "b", "c")
            .map(String::toUpperCase)
            .publishOn(Schedulers.elastic())
            .subscribe(FluxDemo::println, FluxDemo::println, () -> System.out.println("Mission Complete!"), s -> s.request(3L));

        Flux.range(1, 10)
            .handle((i, s) -> {
                if (i % 2 == 0) {
                    s.next("Even: " + i);
                }
            }).subscribe(FluxDemo::println);
        t();
    }

    private static void println(Object o) {
        String name = Thread.currentThread().getName();
        System.out.println("Thread [ " + name + " ] is running..., the val is [ " + o + " ]");
    }

    private static void t() {
        Flux<Double> flux = Flux.create(emitter -> {
            Random rnd = new Random();
            for (int i = 0; i <= 10; i++) {
                emitter.next(rnd.nextDouble());
            }
            int random = rnd.nextInt(2);
            if (random < 1) {
                emitter.complete();
            } else {
                emitter.error(new RuntimeException(
                    "Bad luck, you had one chance out of 2 to complete the Flux"
                ));
            }
        });
        System.out.println(flux.toString());
    }
}
