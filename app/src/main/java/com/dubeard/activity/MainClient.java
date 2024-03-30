package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dubeard.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainClient extends AppCompatActivity {

    FirebaseAuth auth;
    Button sair, btReservar;
    FirebaseUser user;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

        auth = FirebaseAuth.getInstance();
        btReservar = findViewById(R.id.btReservar);
        sair = findViewById(R.id.deslogar);
        textView = findViewById(R.id.infoUsuario);
        user = auth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else{
            textView.setText(user.getEmail());
        }

        btReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Reservar.class);
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