package com.homework.demo.notification;

import com.homework.demo.invitation.api.InvitationEntity;
import com.homework.demo.notification.event.ConfirmInvitationEvent;
import com.homework.demo.notification.event.CreateInvitationEvent;
import com.homework.demo.notification.event.DeclineInvitationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.event.RecordApplicationEvents;

import static com.homework.demo.SampleTestBase.invitation;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@RecordApplicationEvents
class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    private ArgumentCaptor<ConfirmInvitationEvent> confirmInvitationEventArgumentCaptor;

    @Captor
    private ArgumentCaptor<DeclineInvitationEvent> declineInvitationEventArgumentCaptor;

    @Captor
    private ArgumentCaptor<CreateInvitationEvent> createInvitationEventArgumentCaptor;

    @Test
    void publishConfirmInvitationEvent() {
        //given
        InvitationEntity invitation = invitation();
        invitation.setId(1L);
        ConfirmInvitationEvent confirmInvitationEvent = new ConfirmInvitationEvent(this, invitation);
        //when
        notificationService.publishConfirmInvitationEvent(invitation);
        //then
        Mockito.verify(applicationEventPublisher, Mockito.times(1)).publishEvent(confirmInvitationEventArgumentCaptor.capture());
        assertThat(confirmInvitationEvent.getInvitation().getId()).isEqualTo(confirmInvitationEventArgumentCaptor.getValue().getInvitation().getId());
    }

    @Test
    void publishDeclineInvitationEvent() {
        //given
        InvitationEntity invitation = invitation();
        invitation.setId(1L);
        DeclineInvitationEvent declineInvitationEvent = new DeclineInvitationEvent(this, invitation);
        //when
        notificationService.publishDeclineInvitationEvent(invitation);
        //then
        Mockito.verify(applicationEventPublisher, Mockito.times(1)).publishEvent(declineInvitationEventArgumentCaptor.capture());
        assertThat(declineInvitationEvent.getInvitation().getId()).isEqualTo(declineInvitationEventArgumentCaptor.getValue().getInvitation().getId());
    }

    @Test
    void publishCreateInvitationEvent() {
        //given
        InvitationEntity invitation = invitation();
        invitation.setId(1L);
        CreateInvitationEvent createInvitationEvent = new CreateInvitationEvent(this, invitation);
        //when
        notificationService.publishCreateInvitationEvent(invitation);
        //then
        Mockito.verify(applicationEventPublisher, Mockito.times(1)).publishEvent(createInvitationEventArgumentCaptor.capture());
        assertThat(createInvitationEvent.getInvitation().getId()).isEqualTo(createInvitationEventArgumentCaptor.getValue().getInvitation().getId());

    }
}