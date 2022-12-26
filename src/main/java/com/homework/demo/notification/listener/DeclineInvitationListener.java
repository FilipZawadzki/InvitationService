package com.homework.demo.notification.listener;

import com.homework.demo.mail.api.Mail;
import com.homework.demo.mail.api.MailConfig;
import com.homework.demo.mail.api.MailService;
import com.homework.demo.notification.event.DeclineInvitationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeclineInvitationListener implements ApplicationListener<DeclineInvitationEvent> {

    private final MailConfig mailConfig;

    private final MailService mailService;

    @Override
    public void onApplicationEvent(DeclineInvitationEvent event) {
        Mail mail = new Mail();
        mail.setFrom(mailConfig.getFrom());
        mail.setTo(event.getInvitation().getEmail());
        mail.setSubject(mailConfig.getSubject());
        mail.setMessage(mailConfig.getInvDeclinedMessage());
        mailService.sendEmail(mail);
    }
}
