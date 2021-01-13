package cn.az.java.cloud;

import cn.az.java.cloud.stream.UserMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author az
 * @since 2020-04-15
 */
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(UserMessage.class)
public class UserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }
}
