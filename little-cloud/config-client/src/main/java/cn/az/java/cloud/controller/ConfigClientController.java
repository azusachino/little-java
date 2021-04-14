package cn.az.java.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Config client controller.
 *
 * @author azusachino
 * @version 12 /14/2019
 */
@RefreshScope
@RestController
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    /**
     * Get configInfo string.
     *
     * @return the string
     */
    @GetMapping("/config")
    public String getConfigInfo() {
        return configInfo;
    }
}
