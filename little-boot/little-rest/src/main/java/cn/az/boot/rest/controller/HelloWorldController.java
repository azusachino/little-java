package cn.az.boot.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelloWorld {@link Controller}
 *
 * @author az
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String index() {
        return "index";
    }


}
