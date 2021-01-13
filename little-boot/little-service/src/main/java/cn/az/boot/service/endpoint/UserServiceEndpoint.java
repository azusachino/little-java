package cn.az.boot.service.endpoint;

import cn.az.boot.service.domain.User;
import cn.az.boot.service.domain.UserIdRequest;
import cn.az.boot.service.domain.UserResponse;
import cn.az.boot.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * UserServiceEndpoint
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Endpoint
 * @since 2020-03-13
 */
@Endpoint
public class UserServiceEndpoint {


    @Autowired
    private UserRepository userRepository;

    @PayloadRoot(namespace = "http://segmentfault.com/schemas", localPart = "UserIdRequest")
    @ResponsePayload
    public UserResponse getUser(@RequestPayload UserIdRequest userIdRequest) {

        long userId = userIdRequest.getUserId();

        Instant instant = Instant.ofEpochMilli(userIdRequest.getTimestamp());
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println("Web Services 用户 ID :" + userId + " , 请求的时间 : " + zonedDateTime);

        User user = userRepository.findById(userId);

        UserResponse userResponse = new UserResponse();

        userResponse.setUser(user);
        userResponse.setTimestamp(Instant.now().toEpochMilli());

        return userResponse;
    }
}
