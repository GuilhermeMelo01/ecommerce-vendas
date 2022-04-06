package com.guilhermemelo.course.enums;

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Juridica");

    private final int cod;
    private final String descricao;

    TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static TipoCliente toEnum(Integer cod){
        if (cod == null) {
            return null;
        }

        for (TipoCliente cliente: TipoCliente.values()){
            if (cod.equals(cliente.getCod())){
                return cliente;
            }
        }

        throw new IllegalArgumentException("Id is not invalid!"+ cod);
    }
}
