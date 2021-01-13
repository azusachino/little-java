package cn.az.java.cloud.provider.service;

import cn.az.cloud.api.UserService;
import cn.az.cloud.domain.Admin;
import cn.az.java.cloud.stream.UserMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author az
 * @since 2020-04-17
 */
@Service
public class UserMessageService {

    @Autowired
    private UserMessage userMessage;

    @Autowired
    @Qualifier("inMemoryUserService")
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;


    @ServiceActivator(inputChannel = UserMessage.INPUT)
    public void listen(byte[] data) {
        System.out.println("Subscribe by @ServiceActivator");
        saveUser(data);
    }

    @StreamListener(UserMessage.INPUT)
    public void onMessage(byte[] data) {
        System.out.println("Subscribe by @StreamListener");
        saveUser(data);
    }

    private void saveUser(String data) throws IOException {
        Admin admin = objectMapper.readValue(data, Admin.class);
        userService.saveUser(admin);
    }

    private void saveUser(byte[] data) {
        // message body 是字节流 byte[]
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Admin admin = (Admin) objectInputStream.readObject();
            userService.saveUser(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void init() {

        SubscribableChannel subscribableChannel = userMessage.input();
        subscribableChannel.subscribe(message -> {
            System.out.println("Subscribe by SubscribableChannel");
            MimeType mimeType = (MimeType) message.getHeaders().get("contentType");
            assert mimeType != null;
            if (MediaType.APPLICATION_JSON.equals(MediaType.asMediaType(mimeType))) {
                saveUser((byte[]) message.getPayload());
            } else {
                // message body 是字节流 byte[]
                byte[] body = (byte[]) message.getPayload();
                saveUser(body);
            }
        });
    }
}
