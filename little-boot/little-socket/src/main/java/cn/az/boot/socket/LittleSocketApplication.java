package cn.az.boot.socket;

import cn.az.boot.socket.websocket.ChatRoomServerEndPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author az
 */
@EnableWebSocket
@SpringBootApplication
public class LittleSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleSocketApplication.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public ChatRoomServerEndPoint chatRoomServerEndPoint() {
        return new ChatRoomServerEndPoint();
    }
}
