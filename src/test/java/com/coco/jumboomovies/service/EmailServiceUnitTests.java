package com.coco.jumboomovies.service;

import com.coco.jumboomovies.services.EmailService;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceUnitTests {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MessageSource messageSource;

    @ParameterizedTest
    @MethodSource("sendEmail")
    public void sendEmail(String to, String subject, Locale locale, String bodyTemplate) {
        //Arrange
        String token = UUID.randomUUID().toString();
        when(messageSource.getMessage(eq("email.confirmation.subject"), isNull(), eq(locale)))
                .thenReturn(subject);
        when(messageSource.getMessage(eq("email.confirmation.body"), isNull(), eq(locale)))
                .thenReturn(bodyTemplate);

        //Act
        emailService.sendEmail(to, token, locale);

        // Assert
        verify(mailSender, times(1)).createMimeMessage();
        verify(messageSource, times(1)).getMessage("email.confirmation.subject", null, locale);
        verify(messageSource, times(1)).getMessage("email.confirmation.body", null, locale);
    }

    static Stream<Arguments> sendEmail() {
        return Stream.of(
                Arguments.arguments("test1@gmail.com", "Please confirm your email", Locale.ENGLISH, "<p>Hello there,</p><p>Please click the following link to confirm your email:</p><a href=\"%s\">Confirm Your Email</a>"),
                Arguments.arguments("test2@gmail.com", "Veuillez confirmer votre email", Locale.FRENCH, "<p>Bonjour,</p><p>Veuillez cliquer sur le lien suivant pour confirmer votre e-mail :</p><a href=\"%s\">Confirmer votre e-mail</a>"),
                Arguments.arguments("test3@gmail.com", "Conferma la tua email", Locale.ITALIAN, "<p>Ciao,</p><p>Clicca sul seguente link per confermare la tua email:</p><a href=\"%s\">Conferma la tua email</a>")
        );
    }

    @Test
    void testSendEmail() {
        Locale locale = Locale.ENGLISH;
        String token = UUID.randomUUID().toString();

        when(messageSource.getMessage(eq("email.confirmation.subject"), isNull(), eq(locale)))
                .thenReturn("Confirm Your Email");
        when(messageSource.getMessage(eq("email.confirmation.body"), isNull(), eq(locale)))
                .thenReturn("Click here: %s");

        emailService.sendEmail("mail.com", token, locale);

        verify(messageSource).getMessage("email.confirmation.subject", null, locale);
        verify(messageSource).getMessage("email.confirmation.body", null, locale);
    }

    @ParameterizedTest
    @MethodSource("sendEmail")
    void testMimeMessageCreation(String to, String subject, Locale locale, String bodyTemplate) throws Exception {
        // Arrange
        String token = UUID.randomUUID().toString();
        String confirmationLink = "http://localhost:8080/movies/auth/confirm?token=" + token;
        String body = String.format(bodyTemplate, confirmationLink);

        when(messageSource.getMessage("email.confirmation.subject", null, locale)).thenReturn(subject);
        when(messageSource.getMessage("email.confirmation.body", null, locale)).thenReturn(bodyTemplate);

        MimeMessage realMimeMessage = new MimeMessage((Session) null);
        when(mailSender.createMimeMessage()).thenReturn(realMimeMessage);

        // Act
        emailService.sendEmail(to, token, locale);

        // Assert
        assertEquals(subject, realMimeMessage.getSubject()); // Ensure the subject is set correctly
        assertEquals(realMimeMessage.getContent().toString(),body); // Ensure the body is correct
    }
}
