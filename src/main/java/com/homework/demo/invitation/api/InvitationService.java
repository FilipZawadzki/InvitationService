package com.homework.demo.invitation.api;

import java.util.List;
import com.homework.demo.mail.api.Mail;
import com.homework.demo.notification.event.ConfirmInvitationEvent;
import com.homework.demo.notification.event.CreateInvitationEvent;

public interface InvitationService {

    /**
     * Creates invitation in DB changing {@link InvitationStatus} to PENDING
     * and triggers {@link CreateInvitationEvent} which sends inviting {@link Mail} to the invitee.
     *
     * @param invitationDto {@link InvitationDto} of wanted invitation in JSON format
     * @return InvitationDto of created entity
     */
    InvitationDto createInvitation(InvitationDto invitationDto);

    /**
     * Gets all invitations from DB.
     *
     * @return List<InvitationDto> of found entities
     */
    List<InvitationDto> getAllInvitations();

    /**
     * Changes {@link InvitationStatus} of the {@link InvitationEntity} to CONFIRMED and
     * triggers {@link ConfirmInvitationEvent} which sends confirmation {@link Mail} to the invitee.
     *
     * @param invitationId - of the existing invitation
     * @return  InvitationStatus CREATED
     */
    InvitationStatus confirmInvitation(Long invitationId);

    /**
     * Changes {@link InvitationStatus} of the {@link InvitationEntity} to DECLINED and
     * triggers {@link ConfirmInvitationEvent} which sends confirmation {@link Mail} to the invitee.
     *
     * @param invitationId - of the existing invitation
     * @return  InvitationStatus DECLINED
     */
    InvitationStatus declineInvitation(Long invitationId);

}
