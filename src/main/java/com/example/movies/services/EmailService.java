package com.example.movies.services;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.example.movies.model.AppConstants.EMAIL_BODY;
import static com.example.movies.model.AppConstants.EMAIL_SUBJECT;


@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Value("${spring.mail.username}")
    private String fromAddress;

    @Autowired
    public EmailService(JavaMailSender mailSender, MessageSource messageSource) {
        this.mailSender = mailSender;
        this.messageSource = messageSource;
        logger.info("MailSender injected: {}", mailSender != null);
        logger.info("MessageSource injected: {}", messageSource != null);
    }

    public void sendEmail(String to, String token, Locale locale) {

        logger.info("Starting email service");
        try {
            String confirmationLink = "http://localhost:8080/movies/auth/confirm?token=" + token;
            String subject = messageSource.getMessage(EMAIL_SUBJECT, null, locale);
            String bodyTemplate = messageSource.getMessage(EMAIL_BODY, null, locale);
            if (subject == null) {
                subject = "Confirm Your Email";
            }
            if (bodyTemplate == null) {
                bodyTemplate = "<p>Please click the following link to confirm your email:</p><a href=\"%s\">Confirm Your Email</a>";
            }
            String body = String.format(bodyTemplate, confirmationLink);
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // Set 'true' to indicate HTML content
            helper.setFrom(fromAddress);

            logger.info("Starting email service with HTML content");
            this.mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("Error sending email to {}: {}", to, e.getMessage(), e);
        }
    }
}
