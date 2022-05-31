package com.guilhermemelo.course.dto;

import com.guilhermemelo.course.domain.Estado;

import java.io.Serializable;
import java.util.Objects;

public class EstadoDto implements Serializable {

    private Integer id;
    private String nome;

    public EstadoDto(){

    }

    public EstadoDto(Estado estado) {
        id = estado.getId();
        nome = estado.getNome();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoDto estadoDto = (EstadoDto) o;
        return Objects.equals(id, estadoDto.id) && Objects.equals(nome, estadoDto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
