package com.homework.demo.invitation.internal;

import com.homework.demo.exception.InvitationNotFound;
import com.homework.demo.invitation.api.*;
import com.homework.demo.notification.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
class InvitationServiceInternal implements InvitationService {

    private final InvitationRepository invitationRepository;

    private final NotificationService notificationService;

    @Override
    public InvitationDto createInvitation(@Valid InvitationDto invitationDto) {
        InvitationEntity invitation = InvitationMapper.NewDtoToEntity(invitationDto);
        notificationService.publishCreateInvitationEvent(invitation);
        return InvitationMapper.toDto(invitationRepository.save(invitation));
    }

    @Override
    public List<InvitationDto> getAllInvitations() {
        return invitationRepository.findAll().stream().map(InvitationMapper::toDto).toList();
    }

    @Override
    public InvitationStatus confirmInvitation(Long invitationId) {
        Optional<InvitationEntity> invitation = invitationRepository.findById(invitationId);
        if (invitation.isEmpty()) {
            throw new InvitationNotFound();
        }

        invitation.get().setInvitationStatus(InvitationStatus.CONFIRMED);
        invitationRepository.save(invitation.get());
        notificationService.publishConfirmInvitationEvent(invitation.get());
        return InvitationStatus.CONFIRMED;
    }

    @Override
    public InvitationStatus declineInvitation(Long invitationId) {
        Optional<InvitationEntity> invitation = invitationRepository.findById(invitationId);
        if (invitation.isEmpty()) {
            throw new InvitationNotFound();
        }

        invitation.get().setInvitationStatus(InvitationStatus.DECLINED);
        invitationRepository.save(invitation.get());
        notificationService.publishDeclineInvitationEvent(invitation.get());
        return InvitationStatus.DECLINED;
    }
}
