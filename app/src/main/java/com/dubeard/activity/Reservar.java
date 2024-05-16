package com.dubeard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.barber.model.Barber;
import com.dubeard.activity.barber.model.Reserva;
import com.dubeard.activity.barber.model.Service;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class Reservar extends AppCompatActivity {

    private Spinner spinnerHorario, spinnerServico;
    private Button btReservar, btVoltar;

    DatabaseReference databaseReference;
    ArrayAdapter<Service> arrayAdapterServices;
    ArrayList<Service> arrayListServices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        initComponents();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("reserva");

        arrayAdapterServices = new ArrayAdapter<Service>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListServices);
        spinnerServico.setAdapter(arrayAdapterServices);

        setupSpinnerHorario();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Service service = snapshot.getValue(Service.class);
                arrayListServices.add(
                        new Service(snapshot.getKey(),
                                service.getDescricao(),
                                service.getValor())
                );

                arrayAdapterServices.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Service service = snapshot.getValue(Service.class);
              /*  arrayListBarbers.remove(
                        new Barber(snapshot.getKey(),
                                barber.getName(),
                                barber.getPhone(),
                                barber.getEmail())
                );

                arrayAdapterBarbers.notifyDataSetChanged();*/
            }


            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainClient.class);
                startActivity(intent);
            }
        });

        processar();

    }
    private void initComponents() {
        spinnerHorario = findViewById(R.id.spinnerHorario);
        spinnerServico = findViewById(R.id.spinnerServico);
        btReservar = findViewById(R.id.btReservar);
        btVoltar = findViewById(R.id.btvoltar);
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

    private void processar() {
        String horarioSelecionado = spinnerHorario.getSelectedItem().toString();
        String servicoSelecionado = spinnerServico.getSelectedItem().toString();

        btReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.push().setValue(new Reserva(servicoSelecionado, horarioSelecionado));

                databaseReference.push().setValue(spinnerServico.getSelectedItem().toString(),spinnerHorario.getSelectedItem().toString());
                Intent intent = new Intent(getApplicationContext(), MainClient.class);
                startActivity(intent);
            }
        });

    }
}
