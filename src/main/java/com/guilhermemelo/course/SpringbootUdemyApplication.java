package com.guilhermemelo.course;

import com.guilhermemelo.course.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootUdemyApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;


    public static void main(String[] args) {
        SpringApplication.run(SpringbootUdemyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        s3Service.uploadFile("D:\\Users\\User\\Pictures\\Saved Pictures\\gohan_oculos.jpg");
    }
}
