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
import java.util.TimeZone;

public class SelecionarHorario extends SelecionarServico {

    private Button btnDatePicker, btnTimePicker, btReservar, btVoltar;

    private TextView textSelectedDate, textSelectedTime;

    private final String tag = "DATE_TAG";

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_horario);

        initComponents();
        setupDatePicker();
        setupTimePicker();
        setupReservaButton();


    }

    private void initComponents(){
        btnDatePicker = findViewById(R.id.btn_date_picker);
        btnTimePicker = findViewById(R.id.btn_time_picker);
        btReservar = findViewById(R.id.btReservar);
    }

    private void setupDatePicker() {
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        MaterialDatePicker materialDatePicker = builder.build();

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialDatePicker.show(getSupportFragmentManager(), tag);
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        textSelectedDate.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });
    }

    private void setupTimePicker() {
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(SelecionarHorario.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int horasDia, int minutosDia) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, horasDia);
                        c.set(Calendar.MINUTE, minutosDia);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("K:mm a");
                        String time = format.format(c.getTime());
                        textSelectedTime.setText(time);
                    }
                }, hour, mins, false);
                timePickerDialog.show();
            }
        });
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
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        btReservar.setOnClickListener(new View.OnClickListener() {
            Barber barber = new Barber();

            @Override
            public void onClick(View view) {
                Reserva reserva = new Reserva(
                        textSelectedDate.getText().toString(),
                        textSelectedTime.getText().toString(),
                        selectedService.getDescricao(),
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

        Intent intent = new Intent(this, SelecionarProfissional.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}