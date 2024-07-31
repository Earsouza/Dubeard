package com.dubeard.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.barber.model.Service;
import com.dubeard.activity.barber.model.Reserva;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.database.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class Reservar extends AppCompatActivity {

    private Spinner spinnerServico;
    private Button btnDatePicker, btnTimePicker, btReservar, btVoltar, btCancelar;
    private TextView textSelectedDate, textSelectedTime;
    private final String tag = "DATE_TAG";
    private DatabaseReference servicoReference;
    private DatabaseReference reservaReference;
    private ArrayAdapter<Service> arrayAdapterServices;
    private ArrayList<Service> arrayListServices = new ArrayList<>();
    private int i = 0;
    private Service selectedService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar);

        initComponents();

        servicoReference = FirebaseDatabase.getInstance().getReference().child("servico");
        reservaReference = FirebaseDatabase.getInstance().getReference().child("reserva");

        setupServicoListener();
        setupDatePicker();
        setupTimePicker();
        setupReservaButton();
        setupCancelButton();

        arrayAdapterServices = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListServices);
        spinnerServico.setAdapter(arrayAdapterServices);

        // Configurar OnItemSelectedListener para o Spinner
        spinnerServico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedService = (Service) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Implementar o que deve acontecer quando nada é selecionado, se necessário
            }
        });
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

    private void setupServicoListener() {
        servicoReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Service service = snapshot.getValue(Service.class);
                arrayListServices.add(new Service(snapshot.getKey(), service.getDescricao(), service.getValor()));
                arrayAdapterServices.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) { }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
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
    }

    private void setupReservaButton() {
        reservaReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle possible errors.
            }
        });

        btReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reserva reserva = new Reserva(
                        textSelectedDate.getText().toString(),
                        textSelectedTime.getText().toString(),
                        selectedService.getDescricao()
                );

                reservaReference.push().setValue(reserva);

                Intent intent = new Intent(getApplicationContext(), MainClient.class);
                startActivity(intent);
            }
        });
    }

    private void setupCancelButton() {
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });
    }

    private void clearInput() {
        textSelectedDate.setText("");
        textSelectedTime.setText("");
        spinnerServico.setSelection(0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, MainClient.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
