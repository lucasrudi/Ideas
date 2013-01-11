package com.rudilucas.ideas.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service(value = "smtpClient")
public class DefaultSmptClient implements SmtpClient {
    private static final Logger log = LoggerFactory.getLogger(DefaultSmptClient.class);

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendMail(String message, String subject, String mailrecipient) {
        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(mailrecipient);
        simpleMessage.setText(message);
        simpleMessage.setSubject(subject);
        simpleMessage.setSentDate(new Date());
        try {
            sender.send(simpleMessage);
        } catch (MailException exception) {
            log.error("Unable to send mail, reason: ", exception.getRootCause());
        }
    }
}
