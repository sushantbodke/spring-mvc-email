package com.baeldung.spring.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl {

    @Autowired
    private Environment env;

    public void myMailSend (String to, String subject, String text) {
        try {
            JavaMailSenderImpl sender = new JavaMailSenderImpl();
            sender.setHost(env.getProperty("vcap.services.my-email-service.credentials.smtphost"));
            sender.setPort(Integer.parseInt(env.getProperty("vcap.services.my-email-service.credentials.smtpport")));
            sender.setUsername(env.getProperty("vcap.services.my-email-service.credentials.smtpusername"));
            sender.setPassword(env.getProperty("vcap.services.my-email-service.credentials.smtppassword"));
            Properties mailProperties = new Properties();
            mailProperties.put("mail.smtp.auth", Boolean.parseBoolean(env.getProperty("vcap.services.my-email-service.credentials.smtpauth")));
            mailProperties.put("mail.smtp.starttls.enable", Boolean.parseBoolean(env.getProperty("vcap.services.my-email-service.credentials.smtpstarttls")));
            sender.setJavaMailProperties(mailProperties);

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            sender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
// will add this later
//    @Override
//    public void sendSimpleMessageUsingTemplate(String to,
//                                               String subject,
//                                               SimpleMailMessage template,
//                                               String ...templateArgs) {
//        String text = String.format(template.getText(), templateArgs);
//        sendSimpleMessage(to, subject, text);
//    }
//
//    @Override
//    public void sendMessageWithAttachment(String to,
//                                          String subject,
//                                          String text,
//                                          String pathToAttachment) {
//        try {
//            MimeMessage message = emailSender.createMimeMessage();
//            // pass 'true' to the constructor to create a multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text);
//
//            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
//            helper.addAttachment("Invoice", file);
//
//            emailSender.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}
