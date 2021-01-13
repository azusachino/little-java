package cn.az.java.cloud.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author az
 * @since 2020-04-17
 */
public interface UserMessage {

    String INPUT = "user-message";

    @Input(INPUT)
    SubscribableChannel input();
}
