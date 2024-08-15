package com.dubeard.activity.Reserva;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Barber;
import com.dubeard.activity.Admin.model.Reserva;
import com.dubeard.activity.Reserva.SelecionarHorario;
import com.dubeard.activity.Reserva.SelecionarData;
import com.dubeard.activity.Reserva.SelecionarProfissional;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SelecionarHorario extends SelecionarData {

    private Button btReservar;
    private ListView listViewHorarios;
    private String selectedTime;
    private ArrayList<String> intervaloTempo;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_horario);

        Intent intent = getIntent();
        String selectedServiceDescricao = intent.getStringExtra("selectedServiceDescricao");
        String selectedDate = intent.getStringExtra("selectedDate");
        String selectedBarberName  = intent.getStringExtra("selectedBarberName");

        editTextSelectedDate.setText(selectedDate);

        intervaloTempo = new ArrayList<>();
        initComponents();
        popularIntervalos();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, intervaloTempo);
        listViewHorarios.setAdapter(adapter);
        listViewHorarios.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewHorarios.setAdapter(adapter);

        listViewHorarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTime = (String) parent.getItemAtPosition(position);
            }
        });

        DatabaseReference reservaReference = FirebaseDatabase.getInstance().getReference().child("reserva");
        btReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedTime != null) {
                    Reserva reserva = new Reserva(
                            selectedServiceDescricao,
                            selectedBarberName,
                            selectedDate,
                            selectedTime
                    );

                    reservaReference.push().setValue(reserva);

                    Intent intent = new Intent(getApplicationContext(), MainClient.class);
                    startActivity(intent);
                } else {
                    // Tratar caso onde nenhum horário foi selecionado
                    Toast.makeText(SelecionarHorario.this, "Por favor, selecione um horário.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initComponents() {
        listViewHorarios = findViewById(R.id.listViewHorarios);
        btReservar = findViewById(R.id.btReservar);
    }


    private void popularIntervalos() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        while (calendar.get(Calendar.HOUR_OF_DAY) < 18 ||
                (calendar.get(Calendar.HOUR_OF_DAY) == 18 && calendar.get(Calendar.MINUTE) == 0)) {
            intervaloTempo.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.MINUTE, 30);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, SelecionarData.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}