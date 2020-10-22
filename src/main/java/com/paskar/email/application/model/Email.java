package com.paskar.email.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private int id;

    private String recipient;

    private String subject;

    private String body;

    private LocalDate date;

    public Email(String recipient, String subject, String body, LocalDate date) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.date = date;
    }
}
