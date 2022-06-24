package com.guilhermemelo.course.enums;

public enum Perfil {

    CLIENTE(1, "ROLE_CLIENTE"),
    ADMIN(2, "ROLE_ADMIN");

    private Integer cod;
    private String descricao;

    Perfil(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if (cod == null) {
            return null;
        }

        for (Perfil perfis: Perfil.values()){
            if (cod.equals(perfis.getCod())){
                return perfis;
            }
        }

        throw new IllegalArgumentException("Value is not invalid! "+ cod);
    }
}
