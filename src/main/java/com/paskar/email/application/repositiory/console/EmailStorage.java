package com.paskar.email.application.repositiory.console;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.console.EmailRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailStorage implements EmailRepository {
    private final static String baseFile = "emailList.json";

    private final ObjectMapper mapper;

    public EmailStorage(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(List<Email> email) throws IOException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter(baseFile, true)))) {
            mapper.writeValue(out, email);
        }
    }

    @Override
    public List<Email> findAll() throws IOException {
        return mapper.readValue(new File(baseFile), new TypeReference<>() {
        });
    }

    @Override
    public List<Email> findEmailsNearDeliveryDate() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Email> emailList = findAll();
        List<Email> result = new ArrayList<>();

        for (Email email : emailList) {
            if (time.equals(email.getDate())) {
                result.add(email);
            }
        }
        return result;
    }

    @Override
    public void deletingMassagesThatWereSent() throws IOException {
        LocalDateTime time = LocalDateTime.now();
        List<Email> emailList = findAll();
        List<Email> resultList = new ArrayList<>();

        for (Email email : emailList) {
            if (!time.equals(email.getDate())) {
                resultList.add(email);
            }
        }
        mapper.writeValue(new File(baseFile), resultList);
    }
}
