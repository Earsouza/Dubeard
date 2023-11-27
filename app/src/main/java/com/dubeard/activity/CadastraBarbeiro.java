package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;

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
    Button btcadastrar;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_barbeiro);

        iniciarComponente();
        processar();
    }

    private void iniciarComponente(){
        nome = findViewById(R.id.editNome);
        telefone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        btcadastrar = findViewById(R.id.btCadastrarBarbeiro);
    }

    public void processar(){
        reference =  FirebaseDatabase.getInstance().getReference().child("barbeiro");

        Toast.makeText(getApplicationContext(), "Inserido", Toast.LENGTH_LONG).show();

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.push().setValue(new Barbeiro(telefone.getText().toString(),nome.getText().toString(), email.getText().toString()));
            }
        });
    }
}