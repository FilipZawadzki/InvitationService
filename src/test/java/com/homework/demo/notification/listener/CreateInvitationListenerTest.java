package com.homework.demo.notification.listener;

import com.homework.demo.invitation.api.InvitationEntity;
import com.homework.demo.mail.api.Mail;
import com.homework.demo.mail.api.MailConfig;
import com.homework.demo.mail.api.MailService;
import com.homework.demo.notification.event.CreateInvitationEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.RecordApplicationEvents;

import static com.homework.demo.SampleTestBase.invitation;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RecordApplicationEvents
@ExtendWith(MockitoExtension.class)
class CreateInvitationListenerTest {

    private final String MESSAGE = "I invite you to the party!";

    private final String SUBJECT = "Invitation";

    private final String FROM = "invitations@party.com";


    @InjectMocks
    private CreateInvitationListener createInvitationListener;

    @Mock
    private MailConfig mailConfig;

    @Mock
    private MailService mailService;


    @Test
    void onApplicationEvent() {
        //given
        InvitationEntity invitation = invitation();
        Mail mailResult = new Mail();
        mailResult.setMessage(MESSAGE);
        mailResult.setSubject(SUBJECT);
        mailResult.setFrom(FROM);
        mailResult.setTo(invitation.getEmail());
        when(mailConfig.getFrom()).thenReturn(mailResult.getFrom());
        when(mailConfig.getSubject()).thenReturn(mailResult.getSubject());
        when(mailConfig.getInvCreatedMessage()).thenReturn(mailResult.getMessage());
        CreateInvitationEvent createInvitationEvent = new CreateInvitationEvent(this, invitation);
        //when
        createInvitationListener.onApplicationEvent(createInvitationEvent);
        //then
        verify(mailService, Mockito.times(1)).sendEmail(mailResult);
    }
}