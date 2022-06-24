package com.guilhermemelo.course.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOGGER.info("enviando email....");
        mailSender.send(msg);
        LOGGER.info("Email enviado");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOGGER.info("enviando email HTML....");
        javaMailSender.send(msg);
        LOGGER.info("Email enviado");
    }
}
