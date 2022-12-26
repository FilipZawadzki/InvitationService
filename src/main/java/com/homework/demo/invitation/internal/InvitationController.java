package com.homework.demo.invitation.internal;

import com.homework.demo.invitation.api.InvitationDto;
import com.homework.demo.invitation.api.InvitationService;
import com.homework.demo.invitation.api.InvitationStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/invitation")
class InvitationController {

    private final InvitationService invitationService;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    InvitationDto createInvitation(@RequestBody InvitationDto invitationDto) {
        return invitationService.createInvitation(invitationDto);
    }

    @GetMapping(produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    List<InvitationDto> listInvitations() {
        return invitationService.getAllInvitations();
    }

    @PatchMapping(value = "/{id}/confirm", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    InvitationStatus confirmInvitation(@PathVariable("id") Long invitationId) {
        return invitationService.confirmInvitation(invitationId);
    }

    @PatchMapping(value = "/{id}/decline", produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    InvitationStatus declineInvitation(@PathVariable("id") Long invitationId) {
        return invitationService.declineInvitation(invitationId);
    }


}
