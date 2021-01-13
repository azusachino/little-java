package cn.az.boot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author az
 */
@EnableJpaRepositories
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class LittleJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleJpaApplication.class, args);
    }

}
