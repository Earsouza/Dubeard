package com.dubeard.activity.barber.model;

public class Service {

    private String id;
    private String descricao;
    private String valor;

    public Service() {
    }

    public Service(String descricao, String valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Service(String id, String descricao, String valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    @Override
    public String toString() {
        return "Descricao:" + descricao + "\n" + "Valor:" + valor + "\n";
    }


}
