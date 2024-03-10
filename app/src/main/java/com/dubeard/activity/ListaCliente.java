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
import com.dubeard.activity.model.Cliente;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListaCliente extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button btCadastrarCliente;
    ArrayAdapter arrayAdapterClientes;
    ListView listViewClientes;

    ArrayList<String> arrayListClientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_clientes);
        listViewClientes = findViewById(R.id.listViewClientes);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        clicandoNovoCadastroCliente();
        databaseReference = FirebaseDatabase.getInstance().getReference("cliente");


        arrayAdapterClientes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListClientes);
        listViewClientes = (ListView) findViewById(R.id.listViewClientes);
        listViewClientes.setAdapter(arrayAdapterClientes);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Cliente cliente = snapshot.getValue(Cliente.class);
                arrayListClientes.add(new Cliente(cliente.getName(), cliente.getFone(), cliente.getMail()).toString());
                arrayAdapterClientes.notifyDataSetChanged();
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

    private void clicandoNovoCadastroCliente() {
        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastraCliente.class);
                startActivity(intent);
                finish();
            }
        });
    }
}