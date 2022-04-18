package com.guilhermemelo.course.dto;

import com.guilhermemelo.course.domain.Categoria;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class CategoriaDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "name is not must empty")
    @Length(min = 5, max = 80, message = "size must be between 5 and 80")
    private String name;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
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
