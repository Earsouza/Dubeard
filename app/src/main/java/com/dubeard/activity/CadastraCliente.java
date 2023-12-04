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
import com.dubeard.activity.model.Cliente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastraCliente extends AppCompatActivity {

    EditText nome, celular, email;
    Button btCadastrarCliente, btVoltar;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_cliente);

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
        celular = findViewById(R.id.editCelular);
        email = findViewById(R.id.editEmail);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btVoltar = findViewById(R.id.btvoltar);

    }

    public void processar(){
        reference =  FirebaseDatabase.getInstance().getReference().child("cliente");

         btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Cliente(nome.getText().toString(),celular.getText().toString(), email.getText().toString()));
                Intent intent = new Intent(getApplicationContext(), PrincipalProfissional.class);
                startActivity(intent);
            }
        });
    }
}