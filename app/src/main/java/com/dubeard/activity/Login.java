package com.dubeard.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dubeard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText editTextMail, editTextPassword;
    Button botaoLogin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    TextView cadastrarUsuario;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextMail = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editSenha);
        botaoLogin = findViewById(R.id.botaoLogin);
        progressBar = findViewById(R.id.progressBar);
        cadastrarUsuario = findViewById(R.id.cadastroAgora);


        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cadastro.class);
                startActivity(intent);
                finish();
            }
        });
        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                String adminMail = "admin@admin.com";
                String barberMail = "barbeiro@barbeiro.com";
                email = String.valueOf(editTextMail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Informe o e-mail", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Informe a senha", Toast.LENGTH_SHORT).show();
                    return;
                }
               mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    if (email.equals(adminMail)) {
                                        Intent intent = new Intent(getApplicationContext(), MainAdministrator.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (email.equals(barberMail)){
                                        Intent intent = new Intent(getApplicationContext(), MainBarber.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Intent intent = new Intent(getApplicationContext(), MainClient.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    Toast.makeText(Login.this, "Usuário ou senha inválido",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}