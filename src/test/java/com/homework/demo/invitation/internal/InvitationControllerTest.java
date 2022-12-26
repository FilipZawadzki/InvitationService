package com.homework.demo.invitation.internal;

import com.homework.demo.IntegrationTestBase;
import com.homework.demo.invitation.api.InvitationEntity;
import com.homework.demo.invitation.api.InvitationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.homework.demo.SampleTestBase.invitation;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@EnableWebMvc
class InvitationControllerTest extends IntegrationTestBase {

    private MockMvc mockMvc;

    @Autowired
    private InvitationServiceInternal invitationServiceInternal;

    @BeforeEach
    public void setUp() {
        InvitationController invitationController = new InvitationController(invitationServiceInternal);
        mockMvc = MockMvcBuilders.standaloneSetup(invitationController).build();
    }

    @Test
    void createInvitation() throws Exception {
        //given
        InvitationEntity invitation = invitation();
        String content = """
                {
                "invitee": "%s",
                "email": "%s"
                }
                """.formatted(invitation.getInvitee(), invitation.getEmail());

        //when+then
        mockMvc.perform(MockMvcRequestBuilders.post("/invitation").content(content).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.invitee").value(invitation.getInvitee()))
                .andExpect(jsonPath("$.email").value(invitation.getEmail()))
                .andExpect(jsonPath("$.status").value(InvitationStatus.PENDING.toString()));
    }

    @Test
    void listInvitations() throws Exception {
        //given
        InvitationEntity invitation = createInvitation(invitation());

        //when+then
        mockMvc.perform(MockMvcRequestBuilders.get("/invitation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(invitation.getId()))
                .andExpect(jsonPath("$[0].invitee").value(invitation.getInvitee()))
                .andExpect(jsonPath("$[0].email").value(invitation.getEmail()))
                .andExpect(jsonPath("$[0].status").value(InvitationStatus.PENDING.toString()));
    }

    @Test
    void confirmInvitation() throws Exception {
        //given
        InvitationEntity invitation = createInvitation(invitation());

        //when+then
        mockMvc.perform(MockMvcRequestBuilders.patch("/invitation/%s/confirm".formatted(invitation.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(InvitationStatus.CONFIRMED.toString()));
    }

    @Test
    void declineInvitation() throws Exception {
        //given
        InvitationEntity invitation = createInvitation(invitation());

        //when+then
        mockMvc.perform(MockMvcRequestBuilders.patch("/invitation/%s/decline".formatted(invitation.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(InvitationStatus.DECLINED.toString()));
    }
}