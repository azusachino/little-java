package cn.az.boot.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Kafka Controller
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see org.springframework.kafka.core.KafkaTemplate
 * @since 2020-03-12
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaTemplate<? super String, ? super String> kafkaTemplate;

    @PostMapping("/msg/send")
    public String sendMessage(@RequestParam String message) {
        kafkaTemplate.send("sf", 1, System.currentTimeMillis(), "message", message);
        return message;
    }
}
