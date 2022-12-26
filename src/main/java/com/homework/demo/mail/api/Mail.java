package com.homework.demo.mail.api;

import lombok.Data;

@Data
public class Mail {

    private String from;

    private String to;

    private String subject;

    private String message;

}
