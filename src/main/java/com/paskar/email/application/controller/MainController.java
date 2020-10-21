package com.paskar.email.application.controller;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.repositiory.EmailRepository;
import com.paskar.email.application.repositiory.EmailStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final EmailStorage repository;

    @Autowired
    public MainController(EmailStorage repository) {
        this.repository = repository;
    }


    @GetMapping("/main")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/emails")
    @PreAuthorize("hasAnyAuthority('delete')")
    public String showAllEmails(Model model) throws IOException {
        List<Email> emails = repository.findAll();
        model.addAttribute("emails", emails);
        return "emails";
    }

    @GetMapping("/emails-create")
    @PreAuthorize("hasAnyAuthority('read/write')")
    public String createEmail(Model model) {
        return "create_new_email";
    }

    @PostMapping("/emails-create")
    @PreAuthorize("hasAnyAuthority('read/write')")
    public String createNewEmail(@RequestParam String recipient,
                                 @RequestParam String subject,
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                 @RequestParam LocalDateTime dateTime,
                                 @RequestParam String body,
                                 Model model) throws IOException {
        List<Email> list = new ArrayList<>();
        list.add(new Email(recipient, subject, body, dateTime));
        repository.save(list);
        return "redirect:/main";
    }

    @DeleteMapping("/delete/email/{time}")
    @PreAuthorize("hasAnyAuthority('delete')")
    public void deleteByEmailByDate(@PathVariable LocalDateTime time) throws IOException {
        List<Email> emails = repository.findAll();
        emails.removeIf(email -> email.getDate().equals(time));
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }
}
