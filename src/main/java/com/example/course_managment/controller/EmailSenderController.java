package com.example.course_managment.controller;

import com.example.course_managment.features.email.EmailConfig;
import com.example.course_managment.features.email.EmailConfigManager;
import com.example.course_managment.features.email.EmailRequest;
import com.example.course_managment.features.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailSenderController {

    private final EmailService emailService;

    @Autowired
    public EmailSenderController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            emailService.sendEmail(
                    request.getTo(),
                    request.getSubject(),
                    request.getBody()
            );
            return ResponseEntity.ok("email send!");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("error to send email!" + e.getMessage());
        }
    }

    @PostMapping("/config")
    public ResponseEntity<String> config(@RequestBody EmailConfig config) {
        EmailConfig emailConfig = new EmailConfig(
                config.getServerAddress(),
                config.getPort(),
                config.getSecurityType(),
                config.getUsername(),
                config.getPassword()
        );
        EmailConfigManager.save(emailConfig);
        return ResponseEntity.ok("setting saved!");
    }

    @GetMapping("/config")
    public ResponseEntity<EmailConfig> getConfig() {
        EmailConfig emailConfig = EmailConfigManager.load();
        return ResponseEntity.ok(emailConfig);
    }
}
