package cn.az.boot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HelloWorld {@link Controller}
 *
 * @author az
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String index(@RequestParam int value, Model model) {
        // model.addAttribute("acceptLanguage",acceptLanguage);
        // model.addAttribute("jsessionId",jsessionId);
        // model.addAttribute("message","Hello,World");
        return "index";
    }

}
