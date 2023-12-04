package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dubeard.R;
import com.dubeard.activity.model.Barbeiro;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastraBarbeiro extends AppCompatActivity {

    EditText nome, telefone, email;
    Button btCadastrarBarbeiro, btVoltar;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_barbeiro);

        iniciarComponente();
        processar();

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarComponente(){
        nome = findViewById(R.id.editNome);
        telefone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        btCadastrarBarbeiro = findViewById(R.id.btCadastrarBarbeiro);
        btVoltar = findViewById(R.id.btvoltar);

    }

    public void processar(){
        reference =  FirebaseDatabase.getInstance().getReference().child("barbeiro");
        btCadastrarBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Barbeiro(nome.getText().toString(), telefone.getText().toString(), email.getText().toString()));
            }
        });
    }
}