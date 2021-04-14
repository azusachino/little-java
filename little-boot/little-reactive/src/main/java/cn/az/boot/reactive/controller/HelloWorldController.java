package cn.az.boot.reactive.controller;

import cn.az.boot.reactive.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author az
 * @since 2020-04-02
 */
@RestController
public class HelloWorldController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/mvc")
    public String mvc() {
        return "hello world";
    }

    @GetMapping("/{msg}")
    public Mono<?> mono(@PathVariable String msg) {
        return helloService.getHelloWorld(msg);
    }

    @GetMapping("test")
    public Mono<ServerResponse> flux() {
        return ServerResponse.ok().build();
    }

    @GetMapping("/hello")
    public Mono<ServerResponse> hello(ServerRequest req) {
        return ServerResponse.ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(BodyInserters.fromValue("Hello World"));
    }
}
