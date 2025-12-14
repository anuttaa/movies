package com.coco.jumboomovies.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Use your SMTP server (for Gmail, it's smtp.gmail.com)
        mailSender.setPort(587); // The port for STARTTLS is 587
        mailSender.setUsername("telpuknikifor05@gmail.com"); // Replace with your email
        mailSender.setPassword(password); // Replace with your email password

        // Set properties for SMTP (these are important for Gmail)
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true"); // Enable SMTP authentication
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Use TLS 1.2
        props.put("mail.debug", "false"); // Enable debugging to see more details in logs

        return mailSender;
    }
}
