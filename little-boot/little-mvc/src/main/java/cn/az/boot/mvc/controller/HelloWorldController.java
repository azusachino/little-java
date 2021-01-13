package cn.az.boot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * HelloWorld {@link Controller}
 *
 * @author az
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String index(@RequestParam int value, Model model) {
//        model.addAttribute("acceptLanguage",acceptLanguage);
//        model.addAttribute("jsessionId",jsessionId);
//        model.addAttribute("message","Hello,World");
        return "index";
    }

}
