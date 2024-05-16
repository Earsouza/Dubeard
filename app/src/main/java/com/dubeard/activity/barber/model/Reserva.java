package com.dubeard.activity.barber.model;

import android.widget.Spinner;

import java.util.Date;

public class Reserva {
    private Spinner spinnerHorario, spinnerServico;

    private String id;
    private String horario;
    private String servico;

    public Reserva(String id, String horario, String servico) {
        this.id = id;
        this.horario = horario;
        this.servico = servico;
    }

    public Reserva(String horario, String servico) {
        this.horario = horario;
        this.servico = servico;
    }

    public Reserva() {
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
