package cn.az.boot.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author az
 */
@SpringBootApplication
public class LittleKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleKafkaApplication.class, args);
    }


}
