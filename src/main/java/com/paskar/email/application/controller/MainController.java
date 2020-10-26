package com.paskar.email.application.controller;

import com.paskar.email.application.model.Email;
import com.paskar.email.application.service.EmailRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    private final EmailRepositoryImpl repository;

    @Autowired
    public MainController(EmailRepositoryImpl repository) {
        this.repository = repository;
    }


    @GetMapping("/main")
    public String mainPage() {
        return "home";
    }

    @GetMapping("/emails")
    @PreAuthorize("hasAnyAuthority('delete')")
    public String showAllEmails(Model model) throws IOException {
        model.addAttribute("emails", repository.findAll());
        return "emails";
    }

    @GetMapping("/emails-create")
    @PreAuthorize("hasAnyAuthority('read/write')")
    public String createEmail() {
        return "create_new_email";
    }

    @PostMapping("/emails-create")
    @PreAuthorize("hasAnyAuthority('read/write')")
    public String createNewEmail(@RequestBody Email email) throws IOException {
        repository.createEmail(email);
        return "redirect:/main";
    }

    @DeleteMapping("/delete/email/{time}")
    @PreAuthorize("hasAnyAuthority('delete')")
    public void deleteByEmailByDate(@PathVariable LocalDateTime time) throws IOException {
        repository.deleteEmailByDate(time);
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }
}
