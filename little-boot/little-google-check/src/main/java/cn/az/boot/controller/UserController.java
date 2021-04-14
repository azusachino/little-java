package cn.az.boot.controller;

import cn.az.boot.dto.UserDto;
import cn.az.boot.entity.User;
import cn.az.boot.enums.SexEnum;
import cn.az.boot.service.UserService;
import cn.az.boot.util.CommonUtil;
import cn.az.boot.util.QrCodeUtil;
import com.google.zxing.WriterException;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author azusachino
 * @version 12/16/2019
 */
@Controller
@RequestMapping
public class UserController {

    private static final String USER = "az";

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/reg")
    public String reg() {
        return "register";
    }

    @PostMapping("/login")
    public String login(UserDto user) {
        if (userService.login(user)) {
            return "index";
        }
        return "redirect:login";
    }

    @PostMapping("/register")
    public String register(User user, Model model) throws IOException, WriterException {
        user.setPassword(CommonUtil.sha256(user.getPassword()));
        user.setSex(SexEnum.SECRET);
        user.setEmail("azusa@yahoo.com");
        if (userService.save(user)) {
            GoogleAuthenticator ga = new GoogleAuthenticator();
            ga.setCredentialRepository(userService);
            GoogleAuthenticatorKey authenticatorKey = ga.createCredentials(user.getUsername());
            String totpUtl = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(USER, user.getUsername(), authenticatorKey);
            String encodeBase64 = new String(Base64.encodeBase64(QrCodeUtil.createQrCodeToBytes(totpUtl)), StandardCharsets.UTF_8);
            model.addAttribute("qr", "data:image/jpeg;base64," + encodeBase64);
            return "qr_code";
        } else {
            model.addAttribute("err", "register failed");
            return "redirect:reg";
        }
    }

}
