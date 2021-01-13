package cn.az.java.cloud.web.controller;

import cn.az.cloud.api.UserService;
import cn.az.cloud.domain.Admin;
import cn.az.java.cloud.stream.UserMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @author az
 * @since 2020-04-15
 */
@RestController
public class UserServiceClientController implements UserService {

    @Autowired
    private UserService userService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private UserMessage userMessage;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    public UserServiceClientController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("user/save/message")
    public boolean saveUserByMessage(@RequestBody Admin admin) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("az-users", admin);
        return future.isDone();
    }

    @PostMapping("/user/save/message/rabbit")
    public boolean saveUserByRabbitMessage(@RequestBody Admin admin) throws JsonProcessingException {
        MessageChannel messageChannel = userMessage.output();
        // User 序列化成 JSON
        String payload = objectMapper.writeValueAsString(admin);
        GenericMessage<String> message = new GenericMessage<>(payload);
        // 发送消息
        return messageChannel.send(message);
    }

    @PostMapping("/user/save/message/activemq")
    public boolean saveUserByActiveMqMessage(@RequestBody Admin admin) {
        jmsTemplate.convertAndSend(admin);
        return true;
    }

    @PostMapping("/user/save/message/activemq/stream")
    public boolean saveUserByActiveMqStream(@RequestBody Admin admin) {
        MessageChannel messageChannel = userMessage.activeOut();
        GenericMessage<?> message = new GenericMessage<>(admin);
        return messageChannel.send(message);
    }


    /**
     * 通过方法继承，URL 映射 ："/user/save"
     */
    @Override
    public Boolean saveUser(@RequestBody Admin admin) {
        return userService.saveUser(admin);
    }

    /**
     * 通过方法继承，URL 映射 ："/user/find/all"
     */
    @Override
    public Collection<Admin> findAll() {
        return userService.findAll();
    }
}
