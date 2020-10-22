package com.paskar.email.application.repositiory;

import com.paskar.email.application.model.Email;

import java.util.List;

public interface EmailDao {
    List<Email> findAll();

    void save(Email email);

    Email getById(int id);

    void update(Email email);

    void deleteById(int id);

    List<Email> findEmailsNearDeliveryDate();
}
