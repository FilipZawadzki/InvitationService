package com.homework.demo.notification.event;

import com.homework.demo.invitation.api.InvitationEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ConfirmInvitationEvent extends ApplicationEvent {

    private final InvitationEntity invitation;

    public ConfirmInvitationEvent(Object source, InvitationEntity invitation) {
        super(source);
        this.invitation = invitation;
    }
}
