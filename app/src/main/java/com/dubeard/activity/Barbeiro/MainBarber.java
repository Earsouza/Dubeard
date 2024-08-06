package com.dubeard.activity.Barbeiro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Reserva;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainBarber extends AppCompatActivity {

    Intent intent;
    ListView listView;
    DatabaseReference databaseReference;
    ArrayAdapter<Reserva> arrayAdapterReservas;
    ArrayList<Reserva> arrayListReservas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_barber);

        initComponents();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("reserva");

        arrayAdapterReservas = new ArrayAdapter<Reserva>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListReservas);
        listView.setAdapter(arrayAdapterReservas);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Reserva reserva = snapshot.getValue(Reserva.class);
                arrayListReservas.add(
                        new Reserva(snapshot.getKey(),
                                reserva.getHorario(),
                                reserva.getServico())
                );

                arrayAdapterReservas.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                /*Barber barber = snapshot.getValue(Barber.class);
                arrayListBarbers.remove(
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

        //defineButtonsAction();
        //defineListViewAction();
    }

    private void initComponents() {
        listView = findViewById(R.id.listViewAgendamentos);

    }
}