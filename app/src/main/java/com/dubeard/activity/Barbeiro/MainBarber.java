package com.dubeard.activity.Barbeiro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Barber;
import com.dubeard.activity.Admin.model.Reserva;
import com.dubeard.activity.Login;
import com.dubeard.firebase.FirebaseDataManager;
import com.google.firebase.auth.FirebaseAuth;
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

    Button btnSair, btnAtendido;

    FirebaseDataManager<Reserva> firebaseDataManager;



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
                if (reserva != null) {
                    arrayListReservas.add(reserva);
                    arrayAdapterReservas.notifyDataSetChanged();  // Notifica o adapter sobre a mudança na lista
                }
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
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        /*btnAtendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDataManager.getNodeReference().removeValue();
                startActivity(intent);
            }
        });*/
    }

    private void initComponents() {
        listView = findViewById(R.id.listViewAgendamentos);
        btnAtendido = findViewById(R.id.btnAtendido);
        btnSair = findViewById(R.id.btnSair);

        /*String nodeId = getIntent().getStringExtra("id");

        firebaseDataManager = new FirebaseDataManager(Reserva.class, "reserva", nodeId);
        firebaseDataManager.init();*/

    }
}