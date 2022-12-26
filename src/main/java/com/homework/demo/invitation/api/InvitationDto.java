package com.homework.demo.invitation.api;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class InvitationDto {
    private Long id;

    @NotNull
    private String invitee;

    @Email
    private String email;

    private InvitationStatus status;

}
