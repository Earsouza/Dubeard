package com.example.dubeard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginTela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_tela);

        getSupportActionBar().hide();
    }
}