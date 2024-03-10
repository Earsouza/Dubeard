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
import com.dubeard.activity.model.Servico;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListaServico extends AppCompatActivity {

    Button btcadastrarServico;
    ListView listViewServicos;
    DatabaseReference databaseReference;
    ArrayAdapter<String> arrayAdapterServicos;
    ArrayList<String>  arrayListServicos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_servicos);
        btcadastrarServico = findViewById(R.id.cadastrarServico);

        clicandoNovoCadastroServico();

        databaseReference = FirebaseDatabase.getInstance().getReference("servico");

        arrayAdapterServicos = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListServicos);
        listViewServicos = (ListView) findViewById(R.id.listViewServicos);
        listViewServicos.setAdapter(arrayAdapterServicos);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Servico servico = snapshot.getValue(Servico.class);
                arrayListServicos.add(new Servico(servico.getDescricao(), servico.getValor()).toString());
                arrayAdapterServicos.notifyDataSetChanged();
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

    private void clicandoNovoCadastroServico() {
        btcadastrarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastraServico.class);
                startActivity(intent);
                finish();
            }
        });
    }
}