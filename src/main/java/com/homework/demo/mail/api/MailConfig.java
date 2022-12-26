package com.homework.demo.mail.api;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MailConfig {

    private final String invCreatedMessage = "I invite you to the party!";
    private final String invDeclinedMessage = "I decline invitation to the party!";
    private final String invConfirmedMessage = "I accept your invitation to the party!";

    private final String subject = "Invitation";

    private final String from = "invitations@party.com";
}
