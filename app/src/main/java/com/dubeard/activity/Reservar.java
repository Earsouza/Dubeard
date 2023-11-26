package com.dubeard.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;

import java.util.ArrayList;
import java.util.List;

public class Reservar extends AppCompatActivity {

    private Spinner spinnerHorario, spinnerServico;
    private Button btReservar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        spinnerHorario = findViewById(R.id.spinnerHorario);
        spinnerServico = findViewById(R.id.spinnerServico);
        btReservar = findViewById(R.id.btReservar);

        setupSpinnerHorario();
        setupSpinnerServico();

        btReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String horarioSelecionado = spinnerHorario.getSelectedItem().toString();
                String servicoSelecionado = spinnerServico.getSelectedItem().toString();

                String mensagem = "Reservado: Horário - " + horarioSelecionado + ", Serviço - " + servicoSelecionado;
                Toast.makeText(Reservar.this, mensagem, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSpinnerHorario() {
        // Aqui você pode popular o spinner com os horários disponíveis
        List<String> horarios = new ArrayList<>();
        horarios.add("08:00");
        horarios.add("09:00");
        horarios.add("10:00");
        horarios.add("11:00");
        horarios.add("12:00");
        horarios.add("13:00");
        horarios.add("14:00");
        horarios.add("15:00");
        horarios.add("16:00");
        horarios.add("17:00");
        horarios.add("18:00");
        horarios.add("19:00");
        horarios.add("20:00");

        // Adicione mais horários conforme necessário

        ArrayAdapter<String> adapterHorario = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horarios);
        adapterHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(adapterHorario);
    }

    private void setupSpinnerServico() {
        // Aqui você pode popular o spinner com os serviços disponíveis
        List<String> servicos = new ArrayList<>();
        servicos.add("Corte de Cabelo");
        servicos.add("Barba");
        servicos.add("Penteado");
        // Adicione mais serviços conforme necessário

        ArrayAdapter<String> adapterServico = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, servicos);
        adapterServico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerServico.setAdapter(adapterServico);
    }
}
