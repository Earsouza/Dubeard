package com.dubeard.activity;

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
import com.dubeard.activity.model.Barbeiro;
import com.dubeard.activity.model.Servico;
import com.dubeard.adapter.ServicoAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListaBarbeiro extends AppCompatActivity {

    Button btCadastrarBarbeiro;

    ListView listViewBarbeiros;

    ServicoAdapter adapter;

    DatabaseReference reference;

    ArrayList<String>  arrayListBarbeiros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_barbeiros);

        inicializandoComponente();
        clicandoNovoCadastroServico();
    }

    private void clicandoNovoCadastroServico() {
        btCadastrarBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastraBarbeiro.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void inicializandoComponente() {
        btCadastrarBarbeiro = findViewById(R.id.btCadastrarBarbeiro);

        ArrayAdapter<String> arrayAdapterBarbeiros = new ArrayAdapter<String>(ListaBarbeiro.this, android.R.layout.simple_list_item_1, arrayListBarbeiros);

        listViewBarbeiros = (ListView) findViewById(R.id.listViewBarbeiros);
        listViewBarbeiros.setAdapter(arrayAdapterBarbeiros);

        reference = FirebaseDatabase.getInstance().getReference();

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    String nome = snapshot.child("name").getValue(String.class);
                    String telefone = snapshot.child("fone").getValue(String.class);
                    String email = snapshot.child("mail").getValue(String.class);
                    arrayListBarbeiros.add(String.valueOf(new Barbeiro(nome, telefone, email)));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapterBarbeiros.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                arrayAdapterBarbeiros.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayAdapterBarbeiros.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                arrayAdapterBarbeiros.notifyDataSetChanged();

            }
        });

    }
}