package com.paskar.email.application.service;


import com.paskar.email.application.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EmailService {
    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;
    private final EmailRepositoryImpl storage;

    @Autowired
    public EmailService(JavaMailSender emailSender, EmailRepositoryImpl storage) {
        this.emailSender = emailSender;
        this.storage = storage;
    }

    @Scheduled(fixedRate = 60000) //1 min
    public void sendSimpleEmail() throws MailException, IOException {
        List<Email> emailsNearDeliveryDate = storage.findEmailsNearDeliveryDate();
        for (Email emails : emailsNearDeliveryDate) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(emails.getRecipient());
            message.setSubject(emails.getSubject());
            message.setText(emails.getSubject());

            LOG.info("All information about your email {}:", emails.toString());
            this.emailSender.send(message);
        }
        storage.deletingMassagesThatWereSent();
    }
}
