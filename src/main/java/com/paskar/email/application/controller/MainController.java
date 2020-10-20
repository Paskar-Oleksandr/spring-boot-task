package com.paskar.email.application.controller;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final EmailRepository repository;

    @Autowired
    public MainController(EmailRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/emails")
    public String showAllEmails(Model model) throws IOException {
        List<Email> emails = repository.findAll();
        model.addAttribute("emails", emails);
        return "emails";
    }

    @GetMapping("/emails-create")
    public String createEmail(){
        return "create_new_email";
    }

    @PostMapping("/emails-create")
    public String createNewEmail(Email email) throws IOException {
        List<Email> list = new ArrayList<>();
        list.add(email);
        repository.save(list);
        return "redirect:/";
    }

    @DeleteMapping("/delete/email/{time}")
    public void deleteByEmailByDate(@PathVariable LocalDateTime time) throws IOException {
        List<Email> emails = repository.findAll();
        emails.removeIf(email -> email.getDate().equals(time));
    }
}
