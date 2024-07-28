package com.dubeard.activity;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.barber.model.Barber;
import com.dubeard.activity.barber.model.Reserva;
import com.dubeard.activity.barber.model.Service;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Reservar extends AppCompatActivity {

    private Spinner spinnerServico;
    private Button btnDatePicker, btnTimePicker, btReservar, btVoltar, btCancelar;
    private TextView textSelectedDate, textSelectedTime;
    final String tag = "DATE_TAG";
    DatabaseReference databaseReference;
    ArrayAdapter<Service> arrayAdapterServices;
    ArrayList<Service> arrayListServices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        initComponents();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("servico");

        arrayAdapterServices = new ArrayAdapter<Service>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListServices);
        spinnerServico.setAdapter(arrayAdapterServices);

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
            }


            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

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

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(Reservar.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
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

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });
    }

    private void clearInput() {
        textSelectedDate.setText("");
        textSelectedDate.setText("");
    }

    private void initComponents() {
        btReservar = findViewById(R.id.btReservar);
        btVoltar = findViewById(R.id.btvoltar);
        btnDatePicker = findViewById(R.id.btn_date_picker);
        btnTimePicker = findViewById(R.id.btn_time_picker);
        textSelectedTime = findViewById(R.id.text_selected_time);
        textSelectedDate = findViewById(R.id.text_selected_date);
        btCancelar = findViewById(R.id.btCancelarReserva);
        spinnerServico = findViewById(R.id.spinnerServicos);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Defina a tela de destino que você deseja abrir ao pressionar o botão de voltar
        Intent intent = new Intent(this, MainClient.class);

        // Adicione flags para limpar a pilha de atividades, se desejar
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        // Inicie a nova atividade
        startActivity(intent);

        // Finalize a atividade atual, se desejar
        finish();
    }

}
