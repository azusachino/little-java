package cn.az.java.cloud.web.controller;

import cn.az.cloud.domain.Admin;
import cn.az.java.cloud.hystrix.UserHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;

/**
 * @author az
 * @since 2020-04-15
 */
@RestController
public class UserRibbonController {

    /**
     * 负载均衡器客户端
     */
    @Autowired
    private BlockingLoadBalancerClient loadBalancerClient;

    @Value("${provider.service.name}")
    private String providerServiceName;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index() throws IOException {

        // 选择指定的 service Id
        ServiceInstance serviceInstance = loadBalancerClient.choose(providerServiceName);

        return loadBalancerClient.execute(providerServiceName, serviceInstance, instance -> {

            Admin admin = new Admin(99L, "azusachino");
            //服务器实例，获取 主机名（IP） 和 端口
            String host = instance.getHost();
            int port = instance.getPort();
            String url = "http://" + host + ":" + port + "/user/save";
            RestTemplate restTemplate = new RestTemplate();

            return restTemplate.postForObject(url, admin, String.class);

        });
    }

    /**
     * 调用 user-service-provider "/user/list" REST 接口，并且直接返回内容
     * 增加 短路功能
     */
    @GetMapping("/user-service-provider/user/list")
    public Collection<Admin> getUsersList() {
        return new UserHystrixCommand(providerServiceName, restTemplate).run();
    }
}
