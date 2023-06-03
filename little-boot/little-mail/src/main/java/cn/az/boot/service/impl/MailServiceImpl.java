package cn.az.boot.service.impl;

import cn.az.boot.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author Liz
 */
@Slf4j
public class MailServiceImpl implements IMailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * Send simple mail.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        try {
            javaMailSender.send(mailMessage);
            log.info("sending simple Mail");
        } catch (Exception e) {
            log.error("sending mail failed", e);
        }
    }

    /**
     * Send html mail.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
            log.info("sending html mail success");
        } catch (MessagingException e) {
            log.error("sending html mail failed", e);
        }

    }

    /**
     * Send attachments mail.
     *
     * @param to       the to
     * @param subject  the subject
     * @param content  the content
     * @param filePath the file path
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            javaMailSender.send(message);
            log.info("sending html mail with attachment success");
        } catch (MessagingException e) {
            log.error("sending html mail with attachment failed", e);
        }
    }

    /**
     * Send inline resource mail.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     * @param rscPath the rsc path
     * @param rscId   the rsc id
     */
    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            javaMailSender.send(message);
            log.info("嵌入静态资源的邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }
}
