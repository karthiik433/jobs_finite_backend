package com.jobsPortal.jobs_finite.email;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
//        String[] strings = new String[1];
//        strings[0]="anonymous@gmail.com";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("anonymous@gmail.com");
//        message.setTo(strings);
        message.setTo(to);
//        message.setCc();
//        message.setBcc();
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
