package com.baeldung.spring.controllers;

import com.baeldung.spring.mail.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    public EmailServiceImpl emailService;

    @RequestMapping("/mail")
    public String createMailTest() {
        emailService.myMailSend("sushantsharad.bodke@cardinalhealth.com", "Mail using PCF Service Broker", "This mail was sent using Service Broker on PCF");
        return "Mail sent";
    }
}
