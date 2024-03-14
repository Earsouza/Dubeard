package com.dubeard.activity.barber.model;

import android.widget.Spinner;

import java.util.Date;

public class Reserva {
    private Spinner spinnerHorario, spinnerServico;

        private String horario;
        private String servico;

        public Reserva(String horario, String servico) {
            this.horario = horario;
            this.servico = servico;
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
}
