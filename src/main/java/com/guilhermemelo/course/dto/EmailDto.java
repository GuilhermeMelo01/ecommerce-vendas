package com.guilhermemelo.course.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDto implements Serializable {

    @NotEmpty(message = "this field is not must be empty!")
    @Email(message = "email is not valid!")
    private String email;

    public EmailDto(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
