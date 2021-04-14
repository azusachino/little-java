package cn.az.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Liz
 */
@Controller
public class UploadController {

    private static final String UPLOAD_FOLDER = "E://temp//";

    @GetMapping("/")
    public String index() {
        return "upload";
    }


}
