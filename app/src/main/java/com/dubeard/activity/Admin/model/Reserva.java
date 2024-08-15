package com.dubeard.activity.Admin.model;

public class Reserva {
    private String servico;
    private String barbeiro;
    private String dataSelecionada;
    private String horario;

    public Reserva() {
    }
    public Reserva(String key, String horario, String servico) {
    }

    // Construtor com parâmetros
    public Reserva(String servico, String barbeiro, String dataSelecionada, String horarioSelecionado) {
        this.servico = servico;
        this.barbeiro = barbeiro;
        this.dataSelecionada = dataSelecionada;
        this.horario = horarioSelecionado;
    }

    // Getters e Setters
    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(String barbeiro) {
        this.barbeiro = barbeiro;
    }

    public String getDataSelecionada() {
        return dataSelecionada;
    }

    public void setDataSelecionada(String dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "Servico: " + servico + "\n" + "Barbeiro: " + barbeiro + "\n" + "Data: " + dataSelecionada  + "\n" + "Horário: " + horario;

    }
}

