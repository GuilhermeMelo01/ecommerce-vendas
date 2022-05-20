package com.guilhermemelo.course.dto;

import java.io.Serializable;

public class CrendenciasDto implements Serializable {

    private String email;
    private String senha;

    public CrendenciasDto(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
