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
import com.dubeard.activity.Admin.model.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastro extends AppCompatActivity {

    EditText nome, telefone, email, editTextPassword;
    Button buttonRegister;

    DatabaseReference reference;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    TextView textView;

    @Override
    // verificar se o usuário está autenticado
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
        setContentView(R.layout.activity_cadastro);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.editEmail);
        editTextPassword = findViewById(R.id.editSenha);
        nome = findViewById(R.id.editNome);
        buttonRegister = findViewById(R.id.botaoCadastrar);
        progressBar = findViewById(R.id.progressBar);
        telefone = findViewById(R.id.editCelular);
        textView = findViewById(R.id.loginAgora);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String newEmail, newPassword;
                newEmail = String.valueOf(email.getText());
                newPassword = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(newEmail)) {
                    Toast.makeText(Cadastro.this, "Informe o e-mail", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (TextUtils.isEmpty(newPassword)) {
                    Toast.makeText(Cadastro.this, "Informe a senha", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(newEmail, newPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Cadastro.this, "Account created.", Toast.LENGTH_SHORT).show();
                                    createClient();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Cadastro.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void createClient() {
        reference = FirebaseDatabase.getInstance().getReference().child("usuario");
        reference.push().setValue(new Client(
                nome.getText().toString(),
                telefone.getText().toString(),
                email.getText().toString()));
    }
}