package cn.az.java.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StatusController
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @since 2020-03-20
 */
@RestController
@RequestMapping("/status")
public class StatusController {

    @GetMapping
    public String status() {
        return "OK";
    }
}
