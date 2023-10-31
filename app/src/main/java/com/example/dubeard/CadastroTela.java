package com.example.dubeard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CadastroTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_tela);

        getSupportActionBar().hide();

    }
}