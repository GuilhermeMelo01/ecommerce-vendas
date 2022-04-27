package com.guilhermemelo.course.dto;

import com.guilhermemelo.course.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClienteDto implements Serializable {

    private Integer id;
    @NotEmpty(message = "this field is not must be empty!")
    @Length(min = 5, max = 120, message = "size must be between 5 and 120")
    private String nome;

    @NotEmpty(message = "this field is not must be empty!")
    @Email(message = "email is not valid!")
    private String email;


    public ClienteDto(){
    }

    public ClienteDto(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getName();
        email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
