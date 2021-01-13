package cn.az.boot.reactive.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author az
 * @since 01/12/21 21:25
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HelloService {

    private final static Logger LOGGER = Logger.getLogger(HelloService.class.getName());

    private final static WebClient WEB_CLIENT = WebClient.create();

    public Mono<?> getHelloWorld(String msg) {
        return Mono.just(msg)
            .map(x -> x)
            .doOnError(e -> LOGGER.log(Level.SEVERE, "error occurred", e));
    }

    public Mono<?> findById(String id) {
        return WEB_CLIENT
            .get()
            .uri("/users/" + id)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .flatMap(cr -> cr.bodyToMono(Object.class));
    }

    public Flux<?> createReactorObj() {

        Mono<String> helloWorld = Mono.just("Hello World");
        Flux<String> words = Flux.just("Hello", "World");
        Flux<String> manyWords = Flux.fromIterable(Arrays.asList("1", "2"));
        Mono<?> o = Mono.just("")
            .flatMap(Mono::just)
            .map(x -> x)
            .then(Mono.create(MonoSink::success));
        return Flux.empty();
    }
}
