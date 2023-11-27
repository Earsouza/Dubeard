package com.dubeard.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.dubeard.R;
import com.dubeard.activity.model.Barbeiro;
import com.dubeard.activity.model.Servico;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CadastroServico extends AppCompatActivity {

    EditText editDescricao, editValor;
    Button btCadastrar;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_servico);

        inicializandoComponentes();
        cadastrarServico();
    }

    private void inicializandoComponentes() {
        editDescricao = findViewById(R.id.editDescricao);
        editValor = findViewById(R.id.editValor);
        btCadastrar = findViewById(R.id.btCadastrar);

    }

    public void cadastrarServico() {

        reference =  FirebaseDatabase.getInstance().getReference().child("servico");

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Servico(editDescricao.getText().toString(), Double.parseDouble(editValor.getText().toString())));
                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });
    }
}
