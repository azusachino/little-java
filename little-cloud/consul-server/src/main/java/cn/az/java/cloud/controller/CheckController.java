package cn.az.java.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author az
 */
@RestController
public class CheckController {

    @GetMapping("/check")
    public String check() {
        return "OK";
    }

}
