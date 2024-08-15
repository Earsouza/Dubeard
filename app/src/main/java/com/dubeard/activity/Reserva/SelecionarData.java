package com.dubeard.activity.Reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.text.SimpleDateFormat;
import android.widget.CalendarView;
import android.widget.EditText;

import com.dubeard.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SelecionarData extends AppCompatActivity {

    private Button buttonOpenCalendar, btAvancar;
    public EditText editTextSelectedDate;

    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_data);

        initComponents();

        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedDate = editTextSelectedDate.getText().toString();

                Intent intent = getIntent();
                String selectedServiceDescricao = intent.getStringExtra("selectedServiceDescricao");
                String selectedBarberName = intent.getStringExtra("selectedBarberName");

                Intent newIntent = new Intent(getApplicationContext(), SelecionarHorario.class);
                newIntent.putExtra("selectedServiceDescricao", selectedServiceDescricao);
                newIntent.putExtra("selectedBarberName", selectedBarberName);
                newIntent.putExtra("selectedDate", selectedDate);
                startActivity(newIntent);
            }
        });
        calendarView.setMinDate(System.currentTimeMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = formatDate(year, month, dayOfMonth);
                editTextSelectedDate.setText(selectedDate);
            }
        });

    }

    private String formatDate(int year, int month, int dayOfMonth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }

    private void initComponents() {
        calendarView = findViewById(R.id.calendarView);
        btAvancar = findViewById((R.id.btAvancar));
        editTextSelectedDate = findViewById(R.id.editTextSelectedDate);

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