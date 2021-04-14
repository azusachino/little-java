package cn.az.java.cloud.service;

import cn.az.java.cloud.domain.RestResponse;
import cn.az.java.cloud.domain.User;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@FeignClient(value = "user-service", fallback = UserFallBackServiceImpl.class)
public interface UserService {

    @PostMapping("/user/create")
    RestResponse create(@RequestBody User user);

    @GetMapping("/user/{id}")
    RestResponse getUser(@PathVariable Long id);

    @GetMapping("/user/getByUsername")
    RestResponse getByUsername(@RequestParam String username);

    @PostMapping("/user/update")
    RestResponse update(@RequestBody User user);

    @PostMapping("/user/delete/{id}")
    RestResponse delete(@PathVariable Long id);

}
