package cn.az.boot.thymeleaf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.SneakyThrows;

/**
 * @author az
 * @since 2020-03-30
 */
public class LittleThymeleafBootstrap {

    @SneakyThrows
    public static void main(String[] args) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Context context = new Context();

        context.setVariable("message", "hello world");

        // load resource from classpath:/templates/index.html
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/templates/index.html");
        File htmlFile = resource.getFile();
        FileInputStream fis = new FileInputStream(htmlFile);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        IOUtils.copy(fis, baos);
        String result = baos.toString("UTF-8");
        // template content
        // String result = "<p th:text=\"${message}\"></p>";

        System.out.println(templateEngine.process(result, context));
    }
}
