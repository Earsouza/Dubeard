package com.dubeard.activity.Reserva;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dubeard.R;
import com.dubeard.activity.Admin.model.Barber;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SelecionarProfissional extends SelecionarServico {
    private ListView listViewProfissionais;
    private DatabaseReference profissionaisReference;
    private ArrayAdapter<Barber> arrayAdapterProfissionais;
    private ArrayList<Barber> arrayListProfissionais = new ArrayList<>();
    private Button btAvancar;
    public Barber selectedBarber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_profissional);

        initComponents();
        setupProfissionalListener();

        listViewProfissionais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               selectedBarber = (Barber) parent.getItemAtPosition(position);
               Barber barber = new Barber();
               barber.setName(selectedBarber.getName());
            }
        });

        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelecionarHorario.class);
                startActivity(intent);
            }
        });

    }

    private void initComponents() {
        listViewProfissionais = findViewById(R.id.listViewProfissional);
        btAvancar = findViewById(R.id.btAvancar);
    }

    private void setupProfissionalListener() {
        profissionaisReference = FirebaseDatabase.getInstance().getReference().child("barbeiro");
        arrayAdapterProfissionais = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, arrayListProfissionais);
        listViewProfissionais.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewProfissionais.setAdapter(arrayAdapterProfissionais);
        profissionaisReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               Barber barbeiro = snapshot.getValue(Barber.class);
                arrayListProfissionais.add(
                        new Barber(snapshot.getKey(),
                                barbeiro.getName())
                );
                arrayAdapterProfissionais.notifyDataSetChanged();
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
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, SelecionarServico.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}