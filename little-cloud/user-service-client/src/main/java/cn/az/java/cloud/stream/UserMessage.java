package cn.az.java.cloud.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author az
 * @since 2020-04-17
 */
public interface UserMessage {

    @Output("user-message-output")
    MessageChannel output();

    @Output("activemq-out")
    MessageChannel activeOut();
}
