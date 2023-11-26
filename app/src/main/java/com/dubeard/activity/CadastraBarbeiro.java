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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_barbeiro);
    }

    public void processar(View view){


        nome = findViewById(R.id.editNome);
        telefone = findViewById(R.id.editTelefone);
        email = findViewById(R.id.editEmail);
        btcadastrar = findViewById(R.id.btCadastrarBarbeiro);

        String name = nome.getText().toString().trim();
        String fone = telefone.getText().toString().trim();
        String mail = email.getText().toString().trim();

        Barbeiro obj = new Barbeiro(name, fone, mail);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("barbeiro");

        node.child(name).setValue(obj);

        nome.setText("");
        telefone.setText("");
        email.setText("");
        Toast.makeText(getApplicationContext(), "Inserido", Toast.LENGTH_LONG).show();


    }


}