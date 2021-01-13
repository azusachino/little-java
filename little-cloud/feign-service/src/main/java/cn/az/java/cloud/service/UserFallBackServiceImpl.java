package cn.az.java.cloud.service;

import cn.az.java.cloud.domain.RestResponse;
import cn.az.java.cloud.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@Component
public class UserFallBackServiceImpl implements UserService {

    @Override
    public RestResponse create(User user) {
        User u = new User(-1L, "kuma", "123");
        return new RestResponse().data(u);
    }

    @Override
    public RestResponse getUser(Long id) {
        User u = new User(-1L, "kuma", "123");
        return new RestResponse().data(u);
    }

    @Override
    public RestResponse getByUsername(String username) {
        User u = new User(-1L, "kuma", "123");
        return new RestResponse().data(u);
    }

    @Override
    public RestResponse update(User user) {
        return new RestResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message("error, fallback");
    }

    @Override
    public RestResponse delete(Long id) {
        return new RestResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message("error, fallback");
    }
}
