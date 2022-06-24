package com.guilhermemelo.course.config;

import com.guilhermemelo.course.services.DBService;
import com.guilhermemelo.course.services.EmailService;
import com.guilhermemelo.course.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (strategy.equals("create")){
            dbService.instantiateTestDatabase();
            return true;
        }
        return false;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }


}
