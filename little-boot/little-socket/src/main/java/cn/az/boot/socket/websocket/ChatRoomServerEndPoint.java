package cn.az.boot.socket.websocket;

import lombok.SneakyThrows;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author az
 * @date 2020/3/12
 */
@ServerEndpoint("/chat-room/{username}")
public class ChatRoomServerEndPoint {

    /**
     * only one instance
     */
    private static Map<String, Session> livingSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        String sessionId = session.getId();
        livingSessions.put(sessionId, session);
        sendText(session, "welcome [" + username + "]");
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, Session s, String message) {
        sendTextAll("user[" + username + "]: " +message);
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        livingSessions.remove(session.getId());
        sendTextAll("good bye [" + username + "]");

    }

    private void sendTextAll(String message) {
        livingSessions.forEach((k, v) -> sendText(v, message));
    }

    @SneakyThrows
    private void sendText(Session session, String message) {
        RemoteEndpoint.Basic basic = session.getBasicRemote();
        basic.sendText(message);
    }
}
