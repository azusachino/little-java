package cn.az.boot.shiro.controller;

import cn.az.boot.shiro.common.CommonResponse;
import cn.az.boot.shiro.common.CommonUtil;
import cn.az.boot.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 * @date : 2019-07-20 10:22
 **/
@Controller
public class UserController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public CommonResponse login(String username, String password) {
        password = CommonUtil.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return CommonResponse.ok();
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return CommonResponse.error(e.getMessage());
        } catch (AuthenticationException e) {
            return CommonResponse.error("认证失败！");
        }
    }

    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }
}
