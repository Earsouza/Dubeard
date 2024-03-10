package com.dubeard.activity.barber.page;

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
import com.dubeard.activity.barber.model.Barbeiro;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListaBarbeiro extends AppCompatActivity {

    Button btOpenCadBarbeiro;

    ListView listView;

    DatabaseReference databaseReference;

    ArrayAdapter<Barbeiro> arrayAdapterBarbeiros;

    ArrayList<Barbeiro> arrayListBarbeiros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_barbeiros);

        inicializandoComponente();
        clicandoNovoCadastroBarbeiro();

        databaseReference = FirebaseDatabase.getInstance().getReference("barbeiro");

        arrayAdapterBarbeiros = new ArrayAdapter<Barbeiro>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListBarbeiros);
        listView = (ListView) findViewById(R.id.listViewBarbeiros);
        listView.setAdapter(arrayAdapterBarbeiros);

        setupListViewClickListener();

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Barbeiro barbeiro = snapshot.getValue(Barbeiro.class);
                arrayListBarbeiros.add(new Barbeiro(snapshot.getKey(), barbeiro.getName(), barbeiro.getMail(), barbeiro.getFone()));
                arrayAdapterBarbeiros.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Barbeiro barbeiro = snapshot.getValue(Barbeiro.class);
                arrayListBarbeiros.remove(new Barbeiro(snapshot.getKey(), barbeiro.getName(), barbeiro.getMail(), barbeiro.getFone()).toString());
                arrayAdapterBarbeiros.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void inicializandoComponente() {
        btOpenCadBarbeiro = findViewById(R.id.btOpenCadastraBarbeiro);
    }

    private void clicandoNovoCadastroBarbeiro() {
        btOpenCadBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastraBarbeiro.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupListViewClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Barbeiro selectedBarber = (Barbeiro) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListaBarbeiro.this, Edit.class);
                intent.putExtra("id", selectedBarber.getId());
                startActivity(intent);
                finish();
            }
        });
    }
}
