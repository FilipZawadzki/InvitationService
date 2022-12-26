package com.homework.demo;

import com.homework.demo.invitation.api.InvitationEntity;

import java.util.UUID;

public class SampleTestBase {

    public static InvitationEntity invitation() {
        InvitationEntity invitation = new InvitationEntity();
        invitation.setInvitee(UUID.randomUUID().toString());
        invitation.setEmail(UUID.randomUUID().toString());
        return invitation;
    }
}
