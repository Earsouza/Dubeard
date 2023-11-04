package com.dubeard.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dubeard.R;
import com.google.firebase.ktx.Firebase;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void cadastrar(View view){
        Intent i = new Intent(this, Cadastro.class);
        startActivity(i);
    }
}