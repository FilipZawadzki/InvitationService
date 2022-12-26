package com.homework.demo.mail.api;

import com.homework.demo.invitation.api.InvitationEntity;
import com.homework.demo.invitation.api.InvitationStatus;
import com.homework.demo.notification.event.ConfirmInvitationEvent;

public interface MailService {

    /**
     * Sends an email using JavaMailSender.
     *
     * @param mail - mail to be sent
     */
    void sendEmail(Mail mail);

}
