package com.dubeard.activity.model;

public class Servico {
    private String descricao;
    private double valor;

    public Servico() { };

    public Servico(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return  "Descricao:" + descricao + "\n" + "Valor:" + valor + "\n";
    }


}
