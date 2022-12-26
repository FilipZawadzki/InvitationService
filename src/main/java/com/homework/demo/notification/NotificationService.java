package com.homework.demo.notification;

import com.homework.demo.invitation.api.InvitationEntity;
import com.homework.demo.notification.event.ConfirmInvitationEvent;
import com.homework.demo.notification.event.CreateInvitationEvent;
import com.homework.demo.notification.event.DeclineInvitationEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationService {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishConfirmInvitationEvent(InvitationEntity invitation) {
        applicationEventPublisher.publishEvent(new ConfirmInvitationEvent(this, invitation));
    }

    public void publishDeclineInvitationEvent(InvitationEntity invitation) {
        applicationEventPublisher.publishEvent(new DeclineInvitationEvent(this, invitation));
    }

    public void publishCreateInvitationEvent(InvitationEntity invitation) {
        applicationEventPublisher.publishEvent(new CreateInvitationEvent(this, invitation));
    }

}
