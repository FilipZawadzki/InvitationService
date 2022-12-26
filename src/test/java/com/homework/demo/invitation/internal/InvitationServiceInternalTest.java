package com.homework.demo.invitation.internal;

import com.homework.demo.IntegrationTestBase;
import com.homework.demo.invitation.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.homework.demo.SampleTestBase.invitation;
import static org.assertj.core.api.Assertions.assertThat;

class InvitationServiceInternalTest extends IntegrationTestBase {

    @Autowired
    private InvitationService invitationService;

    @Test
    void shouldCreateInvitation_WhenCreatingValidInvitation() {
        //given
        InvitationDto invitation = InvitationMapper.toDto(invitation());
        //when
        InvitationDto result = invitationService.createInvitation(invitation);
        //then
        assertThat(result).isNotNull().extracting(InvitationDto::getId).isNotNull();
        assertThat(result).extracting(InvitationDto::getStatus).isEqualTo(InvitationStatus.PENDING);
    }

    @Test
    void shouldGetAllInvitations_WhenGettingAllInvitations() {
        //given
        InvitationEntity invitation = createInvitation(invitation());
        //when
        List<InvitationDto> result = invitationService.getAllInvitations();
        //then
        assertThat(result).isNotEmpty().extracting(InvitationDto::getId).containsExactlyInAnyOrder(invitation.getId());
    }

    @Test
    void shouldConfirmInvitation_WhenConfirmingInvitation() {
        //given
        InvitationEntity invitation = createInvitation(invitation());
        //when
        InvitationStatus result = invitationService.confirmInvitation(invitation.getId());
        //then
        assertThat(result).isEqualTo(InvitationStatus.CONFIRMED);
    }

    @Test
    void shouldDeclineInvitation_WhenDecliningInvitation() {
        //given
        InvitationEntity invitation = createInvitation(invitation());
        //when
        InvitationStatus result = invitationService.declineInvitation(invitation.getId());
        //then
        assertThat(result).isEqualTo(InvitationStatus.DECLINED);
    }
}