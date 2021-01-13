package cn.az.java.spring.controller;

import cn.az.java.spring.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author az
 * @since 07/30/20
 */
@Controller
public class IndexController {


    @Autowired
    private User u;

    @GetMapping("/index.html")
    public String index(Model model) {
        model.addAttribute("user", u);
        return "index";
    }
}
