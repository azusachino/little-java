package cn.az.boot.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * ConsumerMain
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see a
 * @since 2020-03-12
 */
@Component
public class ConsumerMain {

    @KafkaListener(topics = {"sf"}, groupId = "sf-client")
    public void consumer(String message) {
        System.out.println("customer: " + message);
    }
}
