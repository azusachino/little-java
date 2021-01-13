package cn.az.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author azusachino
 * @version 12/11/2019
 */
@Controller
public class CorsController {

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
