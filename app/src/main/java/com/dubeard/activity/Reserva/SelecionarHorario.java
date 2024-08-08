package com.dubeard.activity.Reserva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Barber;
import com.dubeard.activity.Admin.model.Reserva;
import com.dubeard.activity.Admin.model.Service;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class SelecionarHorario extends AppCompatActivity {

    private Button btReservar;
    private ListView listViewHorarios;
    private TextView textSelectedTime;
    private ArrayList<String> intervaloTempo;


    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_horario);

        intervaloTempo = new ArrayList<>();

        initComponents();
        popularIntervalos();
        setupReservaButton();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, intervaloTempo);
        listViewHorarios.setAdapter(adapter);
        listViewHorarios.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewHorarios.setAdapter(adapter);

    }

    private void initComponents() {
        listViewHorarios = findViewById(R.id.listViewHorarios);

        btReservar = findViewById(R.id.btReservar);
    }


    private void popularIntervalos() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        while (calendar.get(Calendar.HOUR_OF_DAY) < 18 || (calendar.get(Calendar.HOUR_OF_DAY) == 18 && calendar.get(Calendar.MINUTE) == 0)) {
            intervaloTempo.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.MINUTE, 30);
        }
    }

    private void setupReservaButton() {


        DatabaseReference reservaReference = FirebaseDatabase.getInstance().getReference().child("reserva");

        reservaReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btReservar.setOnClickListener(new View.OnClickListener() {
            Barber barber = new Barber();

            @Override
            public void onClick(View view) {
                Reserva reserva = new Reserva(
                        //textSelectedDate.getText().toString(),
                        textSelectedTime.getText().toString(),
                        //selectedService.getDescricao(),
                        barber.getName()
                );

                reservaReference.push().setValue(reserva);

                Intent intent = new Intent(getApplicationContext(), MainClient.class);
                startActivity(intent);
            }
        });

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