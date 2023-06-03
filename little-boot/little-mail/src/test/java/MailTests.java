import cn.az.boot.service.IMailService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.annotation.Resource;

/**
 * @author Liz
 */

@SpringBootTest("")
public class MailTests {

    @Resource
    private IMailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() {
        mailService.sendSimpleMail("ityouknow@126.com", "test simple mail", " hello this is simple mail");
    }

    @Test
    public void testHtmlMail() {
        String content = "<html>\n" +
            "<body>\n" +
            "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
            "</body>\n" +
            "</html>";
        mailService.sendHtmlMail("ityouknow@126.com", "test simple mail", content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath = "e:\\tmp\\application.log";
        mailService.sendAttachmentsMail("ityouknow@126.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content = "<html><body>这是有图片的邮件：<img src='cid:" + rscId + "' ></body></html>";
        String imgPath = "C:\\Users\\summer\\Pictures\\favicon.png";

        mailService.sendInlineResourceMail("ityouknow@126.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }


    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("<h1 th:text={id}></h1>", context);

        mailService.sendHtmlMail("ityouknow@126.com", "主题：这是模板邮件", emailContent);
    }
}
