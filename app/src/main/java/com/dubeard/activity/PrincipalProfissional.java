package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dubeard.R;
import com.dubeard.activity.barber.page.ListaBarbeiro;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PrincipalProfissional extends AppCompatActivity {

    FirebaseAuth auth;
    Button sair, visualizarServico, visualizarBarbeiro, cadastrarCliente;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_profissional);
        visualizarServico = findViewById(R.id.visualizarServico);
        visualizarBarbeiro = findViewById(R.id.visualizarBarbeiro);
        cadastrarCliente = findViewById(R.id.visualizarCliente);

        sair = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        visualizarBarbeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), ListaBarbeiro.class);
                startActivity(intent);
                finish();
            }
        });


        visualizarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaServico.class);
                startActivity(intent);
                finish();
            }
        });

        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), ListaCliente.class);
                startActivity(intent);
                finish();
            }
        });
    }

}