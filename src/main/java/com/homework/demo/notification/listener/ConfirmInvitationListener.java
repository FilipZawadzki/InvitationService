package com.homework.demo.notification.listener;

import com.homework.demo.mail.api.Mail;
import com.homework.demo.mail.api.MailConfig;
import com.homework.demo.mail.api.MailService;
import com.homework.demo.notification.event.ConfirmInvitationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConfirmInvitationListener implements ApplicationListener<ConfirmInvitationEvent> {

    private final MailConfig mailConfig;

    private final MailService mailService;

    @Override
    public void onApplicationEvent(ConfirmInvitationEvent event) {
        Mail mail = new Mail();
        mail.setFrom(mailConfig.getFrom());
        mail.setTo(event.getInvitation().getEmail());
        mail.setSubject(mailConfig.getSubject());
        mail.setMessage(mailConfig.getInvConfirmedMessage());
        mailService.sendEmail(mail);
    }
}
