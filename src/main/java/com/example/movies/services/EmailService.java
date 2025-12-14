package com.example.movies.services;

import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    public EmailService(JavaMailSender mailSender, MessageSource messageSource) {
        this.mailSender = mailSender;
        this.messageSource = messageSource;
        logger.info("MailSender injected: {}", mailSender != null);
        logger.info("MessageSource injected: {}", messageSource != null);
    }

    public void sendEmail(String to, String token, Locale locale) {

        String confirmationLink = "http://localhost:8080/movies/auth/confirm?token=" + token;
        String subject = messageSource.getMessage(EMAIL_SUBJECT, null, locale);
        String bodyTemplate = messageSource.getMessage(EMAIL_BODY, null, locale);
        String body = String.format(bodyTemplate, confirmationLink);

        logger.info("Starting email service");
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // Set 'true' to indicate HTML content
            helper.setFrom("telpuknikifor05@gmail.com");

            logger.info("Starting email service with HTML content");
            this.mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("Error sending email to {}: {}", to, e.getMessage(), e);
        }
    }
}
