package cn.az.java.cloud.controller;

import cn.az.java.cloud.domain.RestResponse;
import cn.az.java.cloud.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author azusachino
 * @version 2019/12/08
 */
@RestController
@RequestMapping("/user")
public class UserRibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    @Value("${}")
    private String instanceName;

    @GetMapping("/")
    public ResponseEntity<String> index() throws IOException {


        ServiceInstance serviceInstance = loadBalancerClient.choose(instanceName);

        return loadBalancerClient.execute(instanceName, serviceInstance, instance -> restTemplate.getForEntity("", String.class, ""));
    }

    @GetMapping("/{id}")
    public RestResponse getUser(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", RestResponse.class, id);
    }

    @GetMapping("/getByUsername")
    public RestResponse getByUsername(@RequestParam String username) {
        return restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", RestResponse.class, username);
    }

    @GetMapping("/getEntityByUsername")
    public RestResponse getEntityByUsername(@RequestParam String username) {
        ResponseEntity<RestResponse> entity = restTemplate.getForEntity(userServiceUrl + "/user/getByUsername?username={1}", RestResponse.class, username);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new RestResponse().code(HttpStatus.INTERNAL_SERVER_ERROR).message("操作失败");
        }
    }

    @PostMapping("/create")
    public RestResponse create(User user) {
        return restTemplate.postForObject(userServiceUrl + "/user/create", user, RestResponse.class);
    }

    @PostMapping("/update")
    public RestResponse update(User user) {
        return restTemplate.postForObject(userServiceUrl + "/user/update", user, RestResponse.class);
    }

    @PostMapping("/delete/{id}")
    public RestResponse delete(@PathVariable Long id) {
        return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null, RestResponse.class, id);
    }
}
