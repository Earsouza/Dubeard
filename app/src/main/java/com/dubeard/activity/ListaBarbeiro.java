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
import com.dubeard.adapter.ServicoAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaBarbeiro extends AppCompatActivity {

    Button btCadastrarBarbeiro;

    ListView listView;

    ServicoAdapter adapter;

    DatabaseReference databaseReference;

    ArrayAdapter<String> arrayAdapterBarbeiros;

    ArrayList<String>  arrayListBarbeiros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_barbeiros);

        inicializandoComponente();
        clicandoNovoCadastroServico();

        databaseReference = FirebaseDatabase.getInstance().getReference("barbeiro");

        arrayAdapterBarbeiros = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayListBarbeiros);
        listView = (ListView) findViewById(R.id.listViewBarbeiros);
        listView.setAdapter(arrayAdapterBarbeiros);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Barbeiro barbeiro = snapshot.getValue(Barbeiro.class);
                arrayListBarbeiros.add(new Barbeiro(barbeiro.name,barbeiro.fone,barbeiro.mail).toString());
                arrayAdapterBarbeiros.notifyDataSetChanged();
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


    }
}