package cn.az.boot.socket.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.RemoteEndpoint;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.SneakyThrows;

/**
 * @author az
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
        sendTextAll("user[" + username + "]: " + message);
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
