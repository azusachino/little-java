package cn.az.java.cloud.ping;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author az
 * @date 2020/4/12
 */
public class MyPing implements IPing {

    @Override
    public boolean isAlive(Server server) {
        String host = server.getHost();
        int port = server.getPort();

//        URI uri = UriComponentsBuilder.fromHttpUrl("http://{host}:{port}/actuator/health")
//                .buildAndExpand(host, port).toUri();

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .port(port)
                .path("/actuator/health")
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<?> res = restTemplate.getForEntity(uri, String.class);
        return HttpStatus.OK.equals(res.getStatusCode());
    }
}
