package com.dubeard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.barber.model.Barber;
import com.dubeard.activity.barber.model.Reserva;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class Reservar extends AppCompatActivity {

    private Spinner spinnerHorario, spinnerServico;
    private Button btReservar, btVoltar;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        spinnerHorario = findViewById(R.id.spinnerHorario);
        spinnerServico = findViewById(R.id.spinnerServico);
        btReservar = findViewById(R.id.btReservar);
        btVoltar = findViewById(R.id.btvoltar);

        setupSpinnerHorario();
        setupSpinnerServico();
        processar();

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalCliente.class);
                startActivity(intent);
            }
        });
    }


    private void setupSpinnerHorario() {
        List<String> horarios = new ArrayList<>();
        horarios.add("08:00");
        horarios.add("08:30");
        horarios.add("09:00");
        horarios.add("09:30");
        horarios.add("10:00");
        horarios.add("10:30");
        horarios.add("11:00");
        horarios.add("11:30");
        horarios.add("12:00");
        horarios.add("12:30");
        horarios.add("13:00");
        horarios.add("13:30");
        horarios.add("14:00");
        horarios.add("14:30");
        horarios.add("15:00");
        horarios.add("15:30");
        horarios.add("16:00");
        horarios.add("16:30");
        horarios.add("17:00");
        horarios.add("17:30");
        horarios.add("18:00");
        horarios.add("18:30");
        horarios.add("19:00");
        horarios.add("19:30");
        horarios.add("20:00");

        ArrayAdapter<String> adapterHorario = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, horarios);
        adapterHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHorario.setAdapter(adapterHorario);
    }

    private void setupSpinnerServico() {
        reference =  FirebaseDatabase.getInstance().getReference().child("reserva");

        //ArrayAdapter<String> adapterServico = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, servicos);
        //adapterServico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerServico.setAdapter(adapterServico);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                /*Servico service = snapshot.getValue(Servico.class);
                arrayListBarbers.add(
                        new Servico(service.getDescricao(),
                                service.getValor()
                );)

                arrayAdapterBarbers.notifyDataSetChanged();*/
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Barber barber = snapshot.getValue(Barber.class);
              /*  arrayListBarbers.remove(
                        new Barber(snapshot.getKey(),
                                barber.getName(),
                                barber.getPhone(),
                                barber.getEmail())
                );

                arrayAdapterBarbers.notifyDataSetChanged();*/
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void processar() {
        String horarioSelecionado = spinnerHorario.getSelectedItem().toString();
        String servicoSelecionado = spinnerServico.getSelectedItem().toString();

        reference =  FirebaseDatabase.getInstance().getReference().child("reserva");
        btReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Reserva(servicoSelecionado, horarioSelecionado));

                reference.push().setValue(spinnerServico.getSelectedItem().toString(),spinnerHorario.getSelectedItem().toString());
                Intent intent = new Intent(getApplicationContext(), PrincipalCliente.class);
                startActivity(intent);
            }
        });

    }
}
