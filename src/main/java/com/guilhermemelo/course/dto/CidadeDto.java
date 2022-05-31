package com.guilhermemelo.course.dto;

import com.guilhermemelo.course.domain.Cidade;

import java.io.Serializable;
import java.util.Objects;

public class CidadeDto implements Serializable {

    private Integer id;
    private String nome;

    public CidadeDto(){

    }

    public CidadeDto(Cidade cidade) {
        id = cidade.getId();
        nome = cidade.getNome();
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
        CidadeDto cidadeDto = (CidadeDto) o;
        return Objects.equals(id, cidadeDto.id) && Objects.equals(nome, cidadeDto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
