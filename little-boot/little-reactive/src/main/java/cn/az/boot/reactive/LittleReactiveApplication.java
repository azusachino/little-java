package cn.az.boot.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author az
 * @date 2020/4/1
 */
@SpringBootApplication
public class LittleReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleReactiveApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        RouterFunctions.route()
                .GET("/hello", req -> ServerResponse.ok().body(Mono.just(req.uri().getPath()), String.class));

        return RouterFunctions.route(req -> {
            String uri = req.uri().getPath();
            return "hello".equals(uri);
        }, req -> ServerResponse.ok().body(Mono.just(req.uri().getPath()), String.class));

    }
}
