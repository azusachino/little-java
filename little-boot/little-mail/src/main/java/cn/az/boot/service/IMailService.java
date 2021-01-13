package cn.az.boot.service;

/**
 * The interface Mail service.
 *
 * @author Liz
 */
public interface IMailService {

    /**
     * Send simple mail.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * Send html mail.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * Send attachments mail.
     *
     * @param to       the to
     * @param subject  the subject
     * @param content  the content
     * @param filePath the file path
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * Send inline resource mail.
     *
     * @param to      the to
     * @param subject the subject
     * @param content the content
     * @param rscPath the rsc path
     * @param rscId   the rsc id
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
