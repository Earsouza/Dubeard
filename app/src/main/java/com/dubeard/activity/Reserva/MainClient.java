package com.dubeard.activity.Reserva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dubeard.R;
import com.dubeard.activity.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainClient extends AppCompatActivity {
    FirebaseAuth auth;
    Button sair, btReservar;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

        auth = FirebaseAuth.getInstance();
        btReservar = findViewById(R.id.btReservar);
        sair = findViewById(R.id.deslogar);
        user = auth.getCurrentUser();

        btReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SelecionarServico.class);
                startActivity(intent);
                finish();
            }
        });
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}