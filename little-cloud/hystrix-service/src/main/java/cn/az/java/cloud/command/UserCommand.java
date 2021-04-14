package cn.az.java.cloud.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

/**
 * {@link com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand}
 *
 * @author az
 * @since 2020-04-13
 */
public class UserCommand extends HystrixCommand<Object> {

    private final String providerServiceName;

    private final RestTemplate restTemplate;

    protected UserCommand(String providerServiceName, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("User-Group"), 100);
        this.providerServiceName = providerServiceName;
        this.restTemplate = restTemplate;
    }

    @Override
    protected Object run() {
        return restTemplate.getForObject("http://" + providerServiceName + "/user/list", Object.class);
    }

    @Override
    protected Object getFallback() {
        return "fallback result";
    }
}
