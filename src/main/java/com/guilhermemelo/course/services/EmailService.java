package com.guilhermemelo.course.services;

import com.guilhermemelo.course.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    
    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage  msg);
}
