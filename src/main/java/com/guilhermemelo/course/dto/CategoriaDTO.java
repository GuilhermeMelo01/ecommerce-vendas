package com.guilhermemelo.course.dto;

import com.guilhermemelo.course.domain.Categoria;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {

    private Integer id;
    private String name;

    public CategoriaDTO(){
    }

    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        name = categoria.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
