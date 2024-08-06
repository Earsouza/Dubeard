package com.dubeard.activity.Admin.Control;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dubeard.R;
import com.dubeard.activity.Admin.Control.BarberList;
import com.dubeard.activity.Admin.Control.ClientList;
import com.dubeard.activity.Admin.Control.ServiceList;
import com.dubeard.activity.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainAdministrator extends AppCompatActivity {

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
                Intent intent = new Intent(getApplicationContext(), BarberList.class);
                startActivity(intent);
                finish();
            }
        });


        visualizarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServiceList.class);
                startActivity(intent);
                finish();
            }
        });

        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClientList.class);
                startActivity(intent);
                finish();
            }
        });
    }

}