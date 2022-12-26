package com.homework.demo.notification.event;

import com.homework.demo.invitation.api.InvitationEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateInvitationEvent extends ApplicationEvent {

    private final InvitationEntity invitation;

    public CreateInvitationEvent(Object source, InvitationEntity invitation) {
        super(source);
        this.invitation = invitation;
    }
}
