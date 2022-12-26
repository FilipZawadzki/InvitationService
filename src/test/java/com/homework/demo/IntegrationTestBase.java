package com.homework.demo;

import com.homework.demo.invitation.api.InvitationEntity;
import com.homework.demo.invitation.api.InvitationRepository;
import com.homework.demo.invitation.api.InvitationService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class IntegrationTestBase {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private InvitationService invitationService;

    @BeforeEach
    void cleanUp() {
        invitationRepository.deleteAll();
    }

    public InvitationEntity createInvitation(InvitationEntity invitation) {
        return invitationRepository.save(invitation);
    }
}
