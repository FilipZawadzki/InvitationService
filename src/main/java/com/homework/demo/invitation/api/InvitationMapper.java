package com.homework.demo.invitation.api;

public class InvitationMapper {
    public static InvitationDto toDto(InvitationEntity invitation) {
        InvitationDto invitationDto = new InvitationDto();
        invitationDto.setId(invitation.getId());
        invitationDto.setInvitee(invitation.getInvitee());
        invitationDto.setEmail(invitation.getEmail());
        if(invitation.getInvitationStatus()!=null){
            invitationDto.setStatus(invitation.getInvitationStatus());
        }
        return invitationDto;
    }

    public static InvitationEntity NewDtoToEntity(InvitationDto invitationDto) {
        InvitationEntity invitation = new InvitationEntity();
        invitation.setEmail(invitationDto.getEmail());
        invitation.setInvitee(invitationDto.getInvitee());
        if(invitationDto.getStatus()!=null){
            invitation.setInvitationStatus(invitationDto.getStatus());
        }
        return invitation;
    }
}
