package com.dubeard.activity.model;

public class Servico {
    private String descricao;
    private double valor;

    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Servico() {
    }
    public String getDescricao() {
        return descricao;
    }
    public double getValor() {
        return valor;
    }

}
