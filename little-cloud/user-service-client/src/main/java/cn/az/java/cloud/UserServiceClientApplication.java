package cn.az.java.cloud;

import cn.az.cloud.api.UserService;
import cn.az.java.cloud.rule.MyRule;
import cn.az.java.cloud.stream.UserMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author az
 */
@EnableCircuitBreaker  // 使用服务短路
@EnableDiscoveryClient // eureka client
@SpringBootApplication
@EnableBinding(UserMessage.class)
@RibbonClient("user-service-provider")
@EnableFeignClients(clients = UserService.class)
public class UserServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceClientApplication.class, args);
    }

    @Bean
    public MyRule myRule() {
        return new MyRule();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
