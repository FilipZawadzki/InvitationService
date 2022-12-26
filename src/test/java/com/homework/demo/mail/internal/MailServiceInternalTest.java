package com.homework.demo.mail.internal;

import com.homework.demo.mail.api.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class MailServiceInternalTest {

    @InjectMocks
    private MailServiceInternal mailServiceInternal;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void sendEmail() {
        //given
        Mail mail = new Mail();
        mail.setTo("testTo@test.com");
        mail.setFrom("testFrom@test.com");
        mail.setTo("testTo@test.com");
        mail.setSubject("subject");
        SimpleMailMessage simpleMailMessageResult = new SimpleMailMessage();
        simpleMailMessageResult.setTo(mail.getTo());
        simpleMailMessageResult.setFrom(mail.getFrom());
        simpleMailMessageResult.setSubject(mail.getSubject());
        //when
        mailServiceInternal.sendEmail(mail);
        //then
        Mockito.verify(javaMailSender, Mockito.times(1)).send(simpleMailMessageResult);
    }
}