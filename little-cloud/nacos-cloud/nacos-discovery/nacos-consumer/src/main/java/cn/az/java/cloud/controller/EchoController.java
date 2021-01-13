package cn.az.java.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author az
 * @since 08/01/20
 */
@RestController
public class EchoController {

    private final RestTemplate restTemplate;

    @Autowired
    public EchoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable String string) {
        return restTemplate.getForObject("http://nacos-provider/echo/" + string, String.class);
    }
}
