package cn.az.java.cloud.ping;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerPing;
import com.netflix.loadbalancer.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * extends {@link com.netflix.loadbalancer.AbstractLoadBalancerPing} or implements {@link com.netflix.loadbalancer.IPing}
 *
 * @author az
 * @since 2020-04-15
 */
public class MyPing extends AbstractLoadBalancerPing {

    @Override
    public boolean isAlive(Server server) {
        String host = server.getHost();
        int port = server.getPort();
        RestTemplate restTemplate = new RestTemplate();
        return HttpStatus.OK
                .equals(restTemplate
                        .getForEntity(UriComponentsBuilder
                                        .newInstance()
                                        .scheme("http")
                                        .host(host)
                                        .port(port)
                                        .path("/acuator/health")
                                        .build()
                                        .toUri(),
                                String.class
                        ).getStatusCode());
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
