package com.dubeard.activity.model;

public class Servico {
    public String descricao;
    public double valor;

    public Servico(){

    }
    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  "Descricao:" + descricao + "\n" + "Valor:" + valor + "\n";
    }


}
