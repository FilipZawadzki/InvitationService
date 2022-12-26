package com.homework.demo.mail.internal;

import com.homework.demo.mail.api.Mail;
import com.homework.demo.mail.api.MailService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class MailServiceInternal implements MailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        javaMailSender.send(message);
    }
}
