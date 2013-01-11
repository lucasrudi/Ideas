package com.rudilucas.ideas.service;

public interface SmtpClient {

    void sendMail(String message, String subject, String mailrecipient);

}
